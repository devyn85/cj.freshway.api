package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MsDistrictValidationReqDto extends CommonDto {
    private String eventType;
    private String ifId;
    private String dccode;
    private String dlvgroupId;
    private String editDate;
    private String editWho;
    private Set<String> serialkeySet;
    private Set<String> dlvgroupIdSet;
    private String delYnFilter;
    private Set<String> dataSet;
    private int seqOffset;
    private Date batchDate;

    private MsDistrictValidationReqDto(Builder builder) {
        if (builder.eventType != null) {
            this.eventType = builder.eventType.toString();
        }
        this.ifId = builder.ifId;
        this.dccode = builder.dccode;
        this.dlvgroupId = builder.dlvgroupId;
        this.editDate = builder.editDate;
        this.editWho = builder.editWho;
        this.serialkeySet = builder.serialkeySet;
        this.dlvgroupIdSet = builder.dlvgroupIdSet;
        this.dataSet = builder.dataSet;
        //noinspection unchecked
        this.setList(builder.list);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private EventType eventType;
        private String ifId;
        private String dccode;
        private String dlvgroupId;
        private String editDate;
        private String editWho;
        private Set<String> serialkeySet;
        private Set<String> dlvgroupIdSet;
        private Set<String> dataSet;
        private List<?> list;

        public Builder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder ifId(String ifId) {
            this.ifId = ifId;
            return this;
        }

        public Builder dccode(String dccode) {
            this.dccode = dccode;
            return this;
        }

        public Builder dlvgroupId(String dlvgroupId) {
            this.dlvgroupId = dlvgroupId;
            return this;
        }

        public Builder editDate(String editDate) {
            this.editDate = editDate;
            return this;
        }

        public Builder editWho(String editWho) {
            this.editWho = editWho;
            return this;
        }

        public Builder serialkeySet(Set<String> serialkeySet) {
            this.serialkeySet = serialkeySet;
            return this;
        }

        public Builder dlvgroupIdSet(Set<String> dlvgroupIdSet) {
            this.dlvgroupIdSet = dlvgroupIdSet;
            return this;
        }

        public Builder dataSet(Set<String> dataSet) {
            this.dataSet = dataSet;
            return this;
        }

        public Builder list(List<?> list) {
            this.list = list;
            return this;
        }

        public MsDistrictValidationReqDto build() {
            return new MsDistrictValidationReqDto(this);
        }
    }

    public enum EventType {
        HJDONG, ORDGRP, POP, DLV_HJDONG, DLV, DLV_GROUP, DLV_POP
    }
}