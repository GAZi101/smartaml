package com.tridaya.smartaml.resource;


import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tridaya.smartaml.mapper.SmartAmlMasterMappers;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class ControllersMaster {

    private SmartAmlMasterMappers smartAmlMasterMappers;

    public ControllersMaster(SmartAmlMasterMappers smartAmlMasterMappers) {
        this.smartAmlMasterMappers = smartAmlMasterMappers;
    }
    // GET Master Pekerjaan
    @GetMapping("/getmasterpekerjaan")
    public String getMasterPekerjaan(@RequestParam(name = "id") String id,
                                     @RequestParam(name = "page") Integer page,
                                     @RequestParam(name = "size") Integer size) throws Exception {
        
        List<HashMap<String, Object>> listMap = new ArrayList<>();
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("takId",id );
        map.put("page",page);
        map.put("size",size);
        JSONObject jso = new JSONObject();
        try {
            listMap = smartAmlMasterMappers.getMasterPekerjaan(map);
            if(listMap.isEmpty()){
                jso.put("Result","Data Not Founds");
                jso.put("TotalRecordCount",listMap.size());
                jso.put("Records",listMap);
            }else{
                jso.put("Result","OK");
                jso.put("Records",listMap);
                jso.put("TotalRecordCount",listMap.get(0).get("totRows").toString());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return jso.toString();
    }
    //POST Master Pekerjaan
    @PostMapping("/getmasterpekerjaan")
    public String saveMasterPekerjaan(@RequestParam(name = "id") String id,
                                      @RequestParam(name = "namapekerjaan") String namaPekerjaan,
                                      @RequestParam(name = "riskProfile") String riskProfile){
        
        List<HashMap<String, Object>> saveData = new ArrayList<>();
        String res = "";
        try{
            
            // dicek dulu sudah ada atau belum id nya
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("takId",id);
            List<HashMap<String, Object>> listMap = smartAmlMasterMappers.getMasterPekerjaan(map);
            
            // set Object 
            Map<String, Object> setMap = new HashMap<String,Object>();
            if(listMap.isEmpty()){
                setMap.put("typ", "save");
            }else{
                setMap.put("typ", "update");
            }
            setMap.put("id", id);
            setMap.put("namapekerjaan",namaPekerjaan);
            setMap.put("riskProfile",riskProfile);
            System.out.println("setMap : " +setMap);
            saveData = smartAmlMasterMappers.addMasterPekerjaan(map);
            res = saveData.get(0).get("RESULT").toString();
            System.out.println("res : " +res);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
