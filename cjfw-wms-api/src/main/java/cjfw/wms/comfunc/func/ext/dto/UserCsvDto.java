package cjfw.wms.comfunc.func.ext.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class UserCsvDto {

    @CsvBindByName(column = "First Name")
    private String firstName;

    @CsvBindByName(column = "Last Name")
    private String lastName;
}
