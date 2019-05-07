package cn.boommanpro.sxu.crawler.model;

import java.util.ArrayList;
import java.util.List;


public class ListWeekStruct {

    public List<Week> Get_ListWeek() {
        Week one = null;
        List<Week> week = new ArrayList<Week>();
        for (int i = 0; i < 22; i++) {
            one = new Week();
            week.add(one);
        }

        return week;
    }

    public class Week {
        public Week() {
            this.Monday = new Day();
            this.Friday = new Day();
            this.Saturday = new Day();
            this.Sunday = new Day();
            this.Thursday = new Day();
            this.Tuesday = new Day();
            this.Wednesday = new Day();
        }

        public void SetData(String Week, String Part, String Course, String Teacher) {
            switch (Week) {
                // TODO: 2018/1/23
                case "一":
                    this.Monday.SetData(Part, Course, Teacher);
                    break;
                case "二":
                    this.Tuesday.SetData(Part, Course, Teacher);
                    break;
                case "三":
                    this.Wednesday.SetData(Part, Course, Teacher);
                    break;
                case "四":
                    this.Thursday.SetData(Part, Course, Teacher);
                    break;
                case "五":
                    this.Friday.SetData(Part, Course, Teacher);
                    break;
                case "六":
                    this.Saturday.SetData(Part, Course, Teacher);
                    break;
                case "日":
                    this.Sunday.SetData(Part, Course, Teacher);
                    break;
                default:
                    System.out.println("周次错误 :" + Week);
                    break;
            }
        }

        public Day GetDay(int WeekNum) {
            switch (WeekNum) {
                case 1:

                    return this.Monday;
                case 2:

                    return this.Tuesday;
                case 3:

                    return this.Wednesday;
                case 4:

                    return this.Thursday;
                case 5:

                    return this.Friday;
                case 6:

                    return this.Saturday;
                case 7:

                    return this.Sunday;
                default:
                    return null;
            }
        }


        //		星期一：Monday
//		星期二：Tuesday
//		星期三：Wednesday
//		星期四：Thursday
//		星期五：Friday
//		星期六：Saturday
//		星期天：Sunday
        private Day Monday;

        public Day getMonday() {
            return Monday;
        }

        public void setMonday(Day monday) {
            Monday = monday;
        }

        public Day getTuesday() {
            return Tuesday;
        }

        public void setTuesday(Day tuesday) {
            Tuesday = tuesday;
        }

        public Day getWednesday() {
            return Wednesday;
        }

        public void setWednesday(Day wednesday) {
            Wednesday = wednesday;
        }

        public Day getThursday() {
            return Thursday;
        }

        public void setThursday(Day thursday) {
            Thursday = thursday;
        }

        public Day getFriday() {
            return Friday;
        }

        public void setFriday(Day friday) {
            Friday = friday;
        }

        public Day getSaturday() {
            return Saturday;
        }

        public void setSaturday(Day saturday) {
            Saturday = saturday;
        }

        public Day getSunday() {
            return Sunday;
        }

        public void setSunday(Day sunday) {
            Sunday = sunday;
        }

        private Day Tuesday;
        private Day Wednesday;
        private Day Thursday;
        private Day Friday;
        private Day Saturday;
        private Day Sunday;

        public class Day {
            public Day() {
                this.one = new Course();
                this.two = new Course();
                this.three = new Course();
                this.five = new Course();
                this.four = new Course();
                this.six = new Course();
                this.seven = new Course();
                this.eight = new Course();
                this.nine = new Course();
                this.ten = new Course();
                this.eleven = new Course();
                this.twelve = new Course();
                this.thirteen = new Course();
                this.fourteen = new Course();
                this.fifteen = new Course();
                this.sixteen = new Course();
            }

            public void SetData(String Part, String Course, String Teacher) {
                switch (Part) {
                    case "1":
                        this.one.setStatue(true);
                        this.one.setCourse(Course);
                        this.one.setTeacher(Teacher);
                        ;
                        break;
                    case "2":
                        this.two.setStatue(true);
                        this.two.setCourse(Course);
                        this.two.setTeacher(Teacher);
                        ;
                        break;
                    case "3":
                        this.three.setStatue(true);
                        this.three.setCourse(Course);
                        this.three.setTeacher(Teacher);
                        ;
                        break;
                    case "4":
                        this.four.setStatue(true);
                        this.four.setCourse(Course);
                        this.four.setTeacher(Teacher);
                        ;
                        break;
                    case "5":
                        this.five.setStatue(true);
                        this.five.setCourse(Course);
                        this.five.setTeacher(Teacher);
                        ;
                        break;
                    case "6":
                        this.six.setStatue(true);
                        this.six.setCourse(Course);
                        this.six.setTeacher(Teacher);
                        ;
                        break;
                    case "7":
                        this.seven.setStatue(true);
                        this.seven.setCourse(Course);
                        this.seven.setTeacher(Teacher);
                        ;
                        break;
                    case "8":
                        this.eight.setStatue(true);
                        this.eight.setCourse(Course);
                        this.eight.setTeacher(Teacher);
                        ;

                        break;
                    case "9":
                        this.nine.setStatue(true);
                        this.nine.setCourse(Course);
                        this.nine.setTeacher(Teacher);
                        ;
                        break;
                    case "10":
                        this.ten.setStatue(true);
                        this.ten.setCourse(Course);
                        this.ten.setTeacher(Teacher);
                        ;
                        break;
                    case "11":
                        this.eleven.setStatue(true);
                        this.eleven.setCourse(Course);
                        this.eleven.setTeacher(Teacher);
                        ;
                        break;
                    case "12":
                        this.twelve.setStatue(true);
                        this.twelve.setCourse(Course);
                        this.twelve.setTeacher(Teacher);
                        ;
                        break;
                    case "13":
                        this.thirteen.setStatue(true);
                        this.thirteen.setCourse(Course);
                        this.thirteen.setTeacher(Teacher);
                        ;
                        break;
                    case "14":
                        this.fourteen.setStatue(true);
                        this.fourteen.setCourse(Course);
                        this.fourteen.setTeacher(Teacher);
                        ;
                        break;
                    case "15":
                        this.fifteen.setStatue(true);
                        this.fifteen.setCourse(Course);
                        this.fifteen.setTeacher(Teacher);
                        ;
                        break;
                    case "16":
                        this.sixteen.setStatue(true);
                        this.sixteen.setCourse(Course);
                        this.sixteen.setTeacher(Teacher);
                        ;
                        break;
                    default:
                        System.out.println("节次错误 :" + Part);
                        break;
                }
            }

            public Course GetCourse(int CourseNum) {
                switch (CourseNum) {
                    case 1:
                        return this.one;
                    case 2:
                        return this.two;
                    case 3:
                        return this.three;
                    case 4:
                        return this.four;
                    case 5:
                        return this.five;
                    case 6:
                        return this.six;
                    case 7:
                        return this.seven;
                    case 8:
                        return this.eight;
                    case 9:
                        return this.nine;
                    case 10:
                        return this.ten;
                    case 11:
                        return this.eleven;
                    case 12:
                        return this.twelve;
                    case 13:
                        return this.thirteen;
                    case 14:
                        return this.fourteen;
                    case 15:
                        return this.fifteen;
                    case 16:
                        return this.sixteen;
                    default:
                        return null;
                }
            }


            private Course one;
            private Course two;
            private Course three;
            private Course four;
            private Course five;
            private Course six;
            private Course seven;
            private Course eight;
            private Course nine;
            private Course ten;
            private Course eleven;
            private Course twelve;
            private Course thirteen;
            private Course fourteen;
            private Course fifteen;
            private Course sixteen;

            public class Course {
                public Course() {
                    this.statue = false;
                    this.Teacher = "";
                    this.Course = "";
                }

                public boolean isStatue() {
                    return statue;
                }

                public void setStatue(boolean statue) {
                    this.statue = statue;
                }

                public String getCourse() {
                    return Course;
                }

                public void setCourse(String course) {
                    Course = course;
                }

                public String getTeacher() {
                    return Teacher;
                }

                public void setTeacher(String teacher) {
                    Teacher = teacher;
                }

                private boolean statue;
                private String Course;
                private String Teacher;


            }
        }
    }
}
