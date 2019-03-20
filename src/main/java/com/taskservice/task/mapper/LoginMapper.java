package com.taskservice.task.mapper;
import com.taskservice.task.po.Login;
import com.taskservice.task.po.LoginExample;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface LoginMapper extends BaseMapper<Login,LoginExample>{
    //    @Select("<script>" +
//            " select s.school_name as schoolName,a.clint_id as clintId,a.position,a.lastlogin_time as lastloginTime,a.state, " +
//            "a.version,a.ip, " +
//            "CAST(" +
//            "             CASE  " +
//            "                  WHEN LEN(a.clint_id)>15 " +
//            "                     THEN '班牌'  " +
//            "                  ELSE  '话机' " +
//            "             END AS varchar ) as type " +
//            "from SmartCampus.dbo.SZ_Attendance  a " +
//            " INNER JOIN " +
//            " SmartCampus.dbo.SZ_School s " +
//            " on a.school_id = s.school_id Where s.school_id != -1 " +
//
//            "<if test=\"schoolName != null\">" +
//            " and s.school_name like #{schoolName} " +
//            "</if>" +
//            "<if test=\"clintId != null\"> " +
//            " and a.clint_id like #{clintId}" +
//            "</if>" +
//            " order by s.school_name "+
//            "</script>")
//    List<SzAttendanceHXYDto> querySzAttendance( @Param("schoolName") String schoolName,@Param("clintId") String clintId);

}
