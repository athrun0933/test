package testFile.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/15.
 */

@Data
public class RunningPointsEntity {
    private int id;
    private int unid;             // 学校号
    private double longitude;     // 经度
    private double latitude;      // 纬度
    private Date createdAt;
    private Date updatedAt;
    private int isFixed;    	  // 是不是固定点位
    private int runMode;    	  // 1校园跑，2操场跑
    private int fromTime;  	      // 有效开始时间/每天
    private int endTime;   	      // 有效结束时间/每天
    private String geohash;       // 经纬度geohash,后续用于搜索
    private String serialNum;     // 点位序列标号
    private String descri;		  // 点位描述
}