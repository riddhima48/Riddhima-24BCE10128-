package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.io.*;
import edu.ccrm.domain.*;
import edu.ccrm.service.*;

import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService ss = new StudentService();
    private static final CourseService cs = new CourseService();
    private static final EnrollmentService es = new EnrollmentService();
    private static final TranscriptService tsrv = new TranscriptService();

    public static void main(String[] args) throws Exception {
        System.out.println("CCRM - Campus Course & Records Manager");
        AppConfig cfg = AppConfig.getInstance();
        Path dataFolder = cfg.getDataFolder();
        Files.createDirectories(dataFolder);
        ImportExportService io = new ImportExportService();
        BackupService bs = new BackupService();

        // seed minimal data
        seedSampleData();

        boolean running = true;
        while(running){
            showMenu();
            String opt = scanner.nextLine().trim();
            switch(opt){
                case "1": manageStudents(); break;
                case "2": manageCourses(); break;
                case "3": manageEnrollment(); break;
                case "4": importExport(io); break;
                case "5": backupAndShow(bs); break;
                case "6": reports(); break;
                case "0": running=false; break;
                default: System.out.println("Unknown option");
            }
        }
        System.out.println("Exiting. Goodbye.");
    }

    private static void showMenu(){
        System.out.println("\nMenu:\n1) Manage Students\n2) Manage Courses\n3) Enrollment & Grades\n4) Import/Export\n5) Backup & Size\n6) Reports\n0) Exit"); 
        System.out.print("Choose: ");
    }

    private static void manageStudents(){
        System.out.println("Students:\n1) Add  2) List  3) Print profile/transcript  4) Deactivate  0) Back"); 
        String o = scanner.nextLine().trim();
        try{
            switch(o){
                case "1":
                    System.out.print("id: "); String id = scanner.nextLine().trim();
                    System.out.print("regNo: "); String reg = scanner.nextLine().trim();
                    System.out.print("name: "); String name = scanner.nextLine().trim();
                    System.out.print("email: "); String email = scanner.nextLine().trim();
                    ss.addStudent(id, reg, name, email);
                    System.out.println("Added.");
                    break;
                case "2":
                    ss.listAll().forEach(s->System.out.println(s.profile()));
                    break;
                case "3":
                    System.out.print("student id: "); String sid = scanner.nextLine().trim();
                    ss.findById(sid).ifPresent(s->{ System.out.println(new TranscriptService().transcript(s)); });
                    break;
                case "4":
                    System.out.print("student id: "); String did = scanner.nextLine().trim();
                    ss.deactivate(did); System.out.println("Done."); break;
                default: break;
            }
        }catch(Exception e){ System.out.println("Error: " + e.getMessage()); }
    }

    private static void manageCourses(){
        System.out.println("Courses:\n1) Add  2) List  0) Back"); 
        String o = scanner.nextLine().trim();
        switch(o){
            case "1":
                System.out.print("code: "); String code = scanner.nextLine().trim();
                System.out.print("title: "); String title = scanner.nextLine().trim();
                System.out.print("credits: "); int credits = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("instructor: "); String instr = scanner.nextLine().trim();
                System.out.print("department: "); String dept = scanner.nextLine().trim();
                Course c = new Course.Builder(code).title(title).credits(credits).instructor(instr).department(dept).build();
                cs.addCourse(c); System.out.println("Added course."); break;
            case "2":
                cs.listAll().forEach(System.out::println); break;
            default: break;
        }
    }

    private static void manageEnrollment(){
        System.out.println("Enrollment:\n1) Enroll 2) Record Marks 3) Unenroll 0) Back");
        String o = scanner.nextLine().trim();
        try{
            switch(o){
                case "1":
                    System.out.print("studentId: "); String sid = scanner.nextLine().trim();
                    System.out.print("courseCode: "); String cc = scanner.nextLine().trim();
                    es.enroll(sid, cc); System.out.println("Enrolled.");
                    break;
                case "2":
                    System.out.print("studentId: "); String sId = scanner.nextLine().trim();
                    System.out.print("courseCode: "); String ccd = scanner.nextLine().trim();
                    System.out.print("marks: "); double m = Double.parseDouble(scanner.nextLine().trim());
                    es.recordMarks(sId, ccd, m); System.out.println("Recorded.");
                    break;
                case "3":
                    System.out.print("studentId: "); String su = scanner.nextLine().trim();
                    System.out.print("courseCode: "); String cu = scanner.nextLine().trim();
                    es.unenroll(su, cu); System.out.println("Unenrolled."); break;
                default: break;
            }
        }catch(Exception e){ System.out.println("Error: " + e.getMessage()); }
    }

    private static void importExport(ImportExportService io){
        try{
            Path st = AppConfig.getInstance().getDataFolder().resolve("export_students.csv");
            Path ct = AppConfig.getInstance().getDataFolder().resolve("export_courses.csv");
            io.exportAll(st, ct);
            System.out.println("Exported to " + st + " and " + ct);
        }catch(Exception e){ System.out.println("Error: " + e.getMessage()); }
    }

    private static void backupAndShow(BackupService bs){
        try{
            Path st = AppConfig.getInstance().getDataFolder().resolve("export_students.csv");
            Path ct = AppConfig.getInstance().getDataFolder().resolve("export_courses.csv");
            Path dest = bs.backup(st, ct);
            long size = bs.recursiveSize(dest);
            System.out.println("Backup created at: " + dest);
            System.out.println("Total backup size: " + size + " bytes");
        }catch(Exception e){ System.out.println("Error: " + e.getMessage()); }
    }

    private static void reports(){
        System.out.println("Reports:\n1) Top students by GPA 0) Back");
        String o = scanner.nextLine().trim();
        if("1".equals(o)){
            ss.topStudentsByGPA(5).forEach(s-> System.out.println(s.getFullName() + " GPA=" + String.format("%.2f", ss.computeGPA(s))));
        }
    }

    private static void seedSampleData(){
        try{
            StudentService sserv = ss;
            CourseService cserv = cs;
            sserv.addStudent("s1","REG001","Alice","alice@example.com");
            sserv.addStudent("s2","REG002","Bob","bob@example.com");
            cserv.addCourse(new Course.Builder("C101").title("Intro to Programming").credits(4).instructor("Dr. X").department("CS").build());
            cserv.addCourse(new Course.Builder("C102").title("Data Structures").credits(4).instructor("Dr. Y").department("CS").build());
        }catch(Exception e){}
    }
}
