package com.wonderskool.bo.utility;

public abstract interface QueryCollection
{
  public static abstract interface PROFESSION_STREAM
  {
    public static final String GET_EXISTING_DATA_ALL = "SELECT profession,stream FROM profession_stream_tbl ORDER BY LOWER(profession)";
    public static final String GET_EXISTING_DATA = "SELECT stream FROM profession_stream_tbl WHERE LOWER(profession) = '?'";
    public static final String DELETE_EXISTING_DATA = "DELETE FROM profession_stream_tbl";
    public static final String INSERT_DATA = "INSERT INTO profession_stream_tbl VALUES (?, ?)";
  }

  public static abstract interface STUDENT_PROFESSION
  {
    public static final String GET_EXISTING_DATA = "SELECT * FROM student_profession WHERE inst_name = '" + Utility.instName + "' AND LOWER(enroll_no) = '?'";
    public static final String DELETE_EXISTING_DATA = "DELETE FROM student_profession WHERE inst_name = '" + Utility.instName + "' AND LOWER(enroll_no) = '?' AND LOWER(profession_trait) = '~'";
    public static final String INSERT_DATA = "INSERT INTO student_profession VALUES (?, ?, ?, ?, ?)";
  }

  public static abstract interface STUDENT_RESULT
  {
    public static final String GET_EXISTING_DATA = "SELECT * FROM student_result WHERE inst_name = '" + Utility.instName + "' AND LOWER(enroll_no) = '?' ORDER BY marks DESC";
    public static final String DELETE_EXISTING_DATA = "DELETE FROM student_result WHERE inst_name = '" + Utility.instName + "'";
    public static final String INSERT_DATA = "INSERT INTO student_result VALUES (?, ?, ?, ?, ?)";
  }

  public static abstract interface PROFESSION_CRITERIA
  {
    public static final String GET_EXISTING_DATA_ALL = "SELECT profession,criteria FROM profession_criteria";
    public static final String DELETE_EXISTING_DATA = "DELETE FROM profession_criteria";
    public static final String INSERT_DATA = "INSERT INTO profession_criteria VALUES (?, ?)";
  }

  public static abstract interface USER_DATA
  {
    public static final String GET_USER_DATA = "SELECT * FROM user_tbl WHERE LOWER(user_id) = ?";
    public static final String GET_USER_MISC_DATA = "SELECT is_admin,~ FROM user_tbl WHERE LOWER(user_id) = ?";
    public static final String DELETE_USER_DATA = "DELETE FROM user_tbl WHERE LOWER(user_id) = ?";
    public static final String INSERT_USER_DATA = "INSERT INTO user_tbl VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_DISTINCT_INST = "SELECT DISTINCT(inst_name) FROM user_tbl ORDER BY inst_name";
  }

  public static abstract interface CODES_MODULE
  {
    public static final String GET_ACTIVE_CODE_DATA = "SELECT code FROM codes_tbl WHERE code_type = ? AND parent_code = ? AND is_deleted = 0 ORDER BY LOWER(code)";
    public static final String GET_ALL_CODE_DATA_4_SCREEN = "SELECT code, is_deleted FROM codes_tbl WHERE code_type = ? AND parent_code = ? ORDER BY LOWER(code)";
    public static final String DELETE_ALL_CODE_DATA_SINGLE = "UPDATE codes_tbl SET is_deleted = ? WHERE code_type = ? AND parent_code = ? AND code = ?";
    public static final String UPDATE_DESC = "UPDATE codes_tbl SET description = ? WHERE code_type = ? AND parent_code = ? AND code = ?";
    public static final String GET_DESC = "SELECT description FROM codes_tbl WHERE code_type = ? AND parent_code = ? AND code = ?";
    public static final String DELETE_ALL_CODE_DATA_ALL = "DELETE FROM codes_tbl WHERE code_type = ? AND parent_code = ?";
    public static final String INSERT_CODE_DATA = "INSERT INTO codes_tbl(code_type, parent_code, code, description, is_deleted) VALUES (?, ?, ?, ?, ?)";
  }

  public static abstract interface STUDENT_PROFILE
  {
    public static final String GET_EXISTING_DATA = "SELECT * FROM student_tbl WHERE LOWER(enrollment_no) = '?' AND inst_name = '" + Utility.instName + "'";
    public static final String GET_EXISTING_DATA_ALL = "SELECT * FROM student_tbl WHERE inst_name = '" + Utility.instName + "'";
    public static final String GET_EXISTING_DATA_CLS = "SELECT * FROM student_tbl WHERE inst_name = '" + Utility.instName + "' AND class = ? ORDER BY LOWER(name)";
    public static final String GET_EXISTING_DATA_CLS_SXN = "SELECT * FROM student_tbl WHERE inst_name = '" + Utility.instName + "' AND class = ? AND section = ? ORDER BY LOWER(name)";
    public static final String DELETE_EXISTING_DATA = "DELETE FROM student_tbl WHERE LOWER(enrollment_no) = '?' AND inst_name = '" + Utility.instName + "'";
    public static final String INSERT_DATA = "INSERT INTO student_tbl VALUES (?, ?, ?, ?, ?, ?)";
  }

  public static abstract interface SYSTEM_VARIABLE
  {
    public static final String GET_EXISTING_DATA = "SELECT * FROM system_variables";
    public static final String GET_PARAM_VAL = "SELECT param_value FROM system_variables WHERE  LOWER(param_name) = '?'";
    public static final String DELETE_EXISTING_DATA = "DELETE FROM system_variables";
    public static final String INSERT_DATA = "INSERT INTO system_variables VALUES (?,?)";
  }

  public static abstract interface COURSE
  {
    public static final String INSERT_DATA = "INSERT INTO course_details(course, type, level) VALUES (?, ?, ?)";
    public static final String GET_DATA = "SELECT * FROM course_details WHERE course = ?";
    public static final String UPDATE_DATA = "UPDATE course_details SET type ?, level = ? WHERE course = ?";
  }

  public static abstract interface QUESTION
  {
    public static final String GET_EXISTING_DATA_ALL = "SELECT question,question_id FROM qa_data WHERE trait = ? AND variable = ? ORDER BY LOWER(question)";
    public static final String GET_EXISTING_DATA = "SELECT * FROM qa_data WHERE question_id = ?";
    public static final String DELETE_EXISTING_DATA = "DELETE FROM qa_data WHERE question_id = ?";
    public static final String MAX_QUESTION_DATA = "SELECT MAX(question_id) FROM qa_data";
    public static final String INSERT_NEW_DATA = "INSERT INTO qa_data VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  }
}