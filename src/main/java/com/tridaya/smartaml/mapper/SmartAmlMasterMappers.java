package com.tridaya.smartaml.mapper;


import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface SmartAmlMasterMappers {

//    @Select(value = " SELECT tak_id AS Id, tak_useraname AS username " +
//            " FROM tbl_ajukankredit WHERE tak_id = #{takId,mode=IN,jdbcType=INTEGER} ")
//    @Options(statementType = StatementType.CALLABLE)
//    List<HashMap<String, Object>> getMasterPekerjaan(Map<String, Object> params);
        
    @Select(value = "{CALL GET_MASTER_PEKERJAAN (#{takId,mode=IN,jdbcType=VARCHAR},"
            + "#{page,mode=IN,jdbcType=INTEGER},"
            + "#{size,mode=IN,jdbcType=INTEGER}"
            + ")}")
    @Options(statementType = StatementType.CALLABLE)
    List<HashMap<String, Object>> getMasterPekerjaan(Map<String, Object> params);
    
    @Select(value = "{CALL SET_MASTER_PEKERJAAN (#{typ, mode=IN, jdbcType = VARCHAR},"
				+ "#{id, mode=IN, jdbcType = VARCHAR}," 
				+ "#{namapekerjaan, mode=IN, jdbcType = VARCHAR},"
				+ "#{riskProfile, mode=IN, jdbcType = VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    List<HashMap<String, Object>> addMasterPekerjaan(Map<String, Object> params);
}
