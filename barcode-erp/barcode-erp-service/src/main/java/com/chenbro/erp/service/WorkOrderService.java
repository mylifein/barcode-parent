package com.chenbro.erp.service;

import com.chenbro.erp.mapper.WorkOrderMapper;
import com.chenbro.erp.pojo.ErpCost;
import com.chenbro.erp.pojo.ErpCostType;
import com.chenbro.erp.pojo.ErpStock;
import com.chenbro.erp.pojo.WorkOrder;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName WorkOrderService
 * @Description TODO
 * @Author z8777
 * @Date 2021/2/7 11:35
 * @Version 1.0
 **/
@Service
public class WorkOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkOrderService.class);

    @Autowired
    private WorkOrderMapper workOrderMapper;

    public WorkOrder queryWOByWO(String wo) {
        WorkOrder workOrder = new WorkOrder();
        workOrder.setWoNo(wo);
        return workOrderMapper.selectOne(workOrder);
    }

    public List<ErpStock> selectStocks() {
        return workOrderMapper.selectStocks();
    }

    public List<ErpStock> selectVMIStocks() {
        return workOrderMapper.selectVMIStocks();
    }


    public List<ErpCost> selectErpCosts(Integer orgId, String costType, String segment, MultipartFile file) throws IOException {
        List<String> segments = new ArrayList<>();
        List<String> costTypes = new ArrayList<>();
        if (StringUtils.isBlank(segment) || StringUtils.isBlank(costType)) {
            String filename = file.getOriginalFilename();
            if (StringUtils.isBlank(filename)) {
                LOGGER.info("文件不能爲空： {}");
                throw new IOException("文件不能爲空");
            }
            if (!filename.endsWith("xls") && !filename.endsWith("xlsx")) {
                LOGGER.info("文件类型不合法： {}", filename);
                throw new IOException(filename + "不是excel文件");
            }
            Workbook workbook = null;
            if (filename.endsWith("xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
            Sheet sheet = workbook.getSheetAt(0);
            if (StringUtils.isBlank(segment)) {
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    String firstCell = row.getCell(0).getStringCellValue();
                    segments.add(firstCell);
                }
            }
            if (StringUtils.isBlank(costType)) {
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(1);
                    if (ObjectUtils.isEmpty(cell)) {
                        break;
                    }
                    cell.setCellType(CellType.STRING);
                    String secondCell = cell.getStringCellValue();
                    if (!StringUtils.isBlank(secondCell)) {
                        costTypes.add(secondCell);
                    }
                }
            }
        } else {
            segments.add(segment);
            costTypes.add(costType);
        }
        return workOrderMapper.selectErpCost(orgId, costTypes, segments);
    }


    public List<ErpCostType> selectErpCostType() {
        return workOrderMapper.selectErpCostType();
    }
}
