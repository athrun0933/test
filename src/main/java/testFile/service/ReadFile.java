package testFile.service;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import testFile.entity.RunningPointsEntity;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class ReadFile {
    private List<List<String>> readXlsx(String path) throws Exception{
        InputStream is = new FileInputStream(path);
        List<List<String>> result = new ArrayList<List<String>>();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        int minRowIx = xssfSheet.getFirstRowNum();
        for(int rowNum = minRowIx; rowNum <= xssfSheet.getLastRowNum();rowNum++){
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            int minColIx = xssfRow.getFirstCellNum();
            int maxColIx = xssfRow.getLastCellNum();
            List<String> rowList = new ArrayList<String>();
            for(int colIx = minColIx;colIx <= maxColIx; colIx++){
                XSSFCell cell = xssfRow.getCell(colIx);
                rowList.add(cell.toString());
            }
            result.add(rowList);
        }


        return result;
    }

    private String makeSQL(List<List<String>> excel, int unid) throws Exception{
        StringBuilder resultSQL = new StringBuilder("insert into swcampus.UniversityGoalRunningPoints(unid,longitude,latitude,created_at,updated_at,is_fixed,run_mode,from_time,end_time,geoHash, serial_num,descri) values");
        for (List<String>row:excel) {
            resultSQL.append(converter(row));
        }
        return resultSQL.toString();
    }

    private RunningPointsEntity converter(List<String> row) {
        String[] location =  row.get(1).split(",");
        RunningPointsEntity runningPointsEntity = new RunningPointsEntity();
        runningPointsEntity.setLongitude(Double.parseDouble(location[0]));
        runningPointsEntity.setLatitude(Double.parseDouble(location[1]));
        runningPointsEntity.setIsFixed(0);
        runningPointsEntity.setRunMode(1);
        runningPointsEntity.setFromTime(0);
        runningPointsEntity.setEndTime(23);
        runningPointsEntity.setSerialNum(row.get(0));
        runningPointsEntity.setDescri(row.get(3));
        return runningPointsEntity;
    }
}
