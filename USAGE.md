# USAGE

1. Compile sources:
   mkdir -p out
   javac -d out $(find src -name "*.java")

2. Run:
   java -cp out edu.ccrm.cli.Main

3. From menu, try:
   - Manage Students -> Add, List
   - Manage Courses -> Add, List
   - Enrollment -> Enroll student s1 into C101, then Record Marks

4. Export data -> will create export CSVs under data/
   Backup -> will create a timestamped backup under data/
