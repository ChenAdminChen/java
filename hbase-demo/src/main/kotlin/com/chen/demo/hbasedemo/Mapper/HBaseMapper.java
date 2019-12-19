package com.chen.demo.hbasedemo.Mapper;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HBaseMapper {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    public List<Map<String, Object>> processFindData(String tableName, Scan scan) {
        List list=new ArrayList();
        list= hbaseTemplate.find(tableName, scan, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(Result result, int i) throws Exception {
                List<Cell> cells = result.listCells();
                Map<String, Object> map = new HashMap<String, Object>();
                if (cells != null && cells.size() > 0) {
                    for (Cell cell : cells) {
                        map.put(
                                Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()),
                                Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength())
                        );
                    }
                }
                return map;
            }
        });
        return list;
    }


    public Scan setScan(int uid, String startTime, String endTime, Integer source) {
        Scan scan = new Scan();
        if (source==null){
            scan.withStartRow(Bytes.toBytes(uid + "_" + startTime));
            scan.withStopRow(Bytes.toBytes(uid + "_" + endTime));
        }else {
            scan.withStartRow(Bytes.toBytes(uid + "_" + startTime + "_" + source));
            scan.withStopRow(Bytes.toBytes(uid + "_" + endTime + "_" + source));
        }
//        设置读取到内存的条数
        return scan;
    }
}
