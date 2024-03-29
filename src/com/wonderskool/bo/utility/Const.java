package com.wonderskool.bo.utility;

import java.util.ArrayList;
import java.util.List;

public abstract interface Const
{
  public static class SYST_VAR_PROP
  {
    public static final String STATIC_FILE_PATH = "STATIC_FILE";
    public static final String DESTINATION_FILE_PATH = "DESTINATION_FILE";
    public static final String SYST_VAR_NAME = "SYST_VAR";
    public static final String DRIVER_CLASS = "DRIVER_CLASS";
    public static final String CONN_URL = "CONN_URL";
    public static final String DB_USER_NAME = "DB_USER_NAME";
    public static final String DB_PASSWORD = "DB_PASSWORD";
  }

  public static class Code_Type {
    public static final String MI = "Multiple Intelligences";
    public static final String CMPLX_PERS = "Complex Personality Graph";
    public static final String ABILITY = "Ability Graph";
    public static final String QUALITY = "Quality Graph";
    public static final String MOTIVATORS = "Motivators";
    public static final String WORKING_STYLE = "Style of Working";
    public static final String COURSE = "Course";
    public static final String COURSE_LEVEL = "Course Level";
    public static final String COURSE_TYPE = "Course Type";

    public static List<String> getLst() {
      List<String> dataLst = new ArrayList<String>();
      dataLst.add(MI);
      dataLst.add(CMPLX_PERS);
      dataLst.add(ABILITY);
      dataLst.add(QUALITY);
      dataLst.add(MOTIVATORS);
      dataLst.add(WORKING_STYLE);
      dataLst.add(COURSE);
      dataLst.add(COURSE_LEVEL);
      dataLst.add(COURSE_TYPE);
      return dataLst;
    }

    public static List<String> getProfTraitLst() {
      List<String> dataLst = new ArrayList<String>();
      dataLst.add(MI);
      dataLst.add(CMPLX_PERS);
      dataLst.add(ABILITY);
      dataLst.add(QUALITY);
      dataLst.add(MOTIVATORS);
      dataLst.add(WORKING_STYLE);
      return dataLst;
    }
  }
}