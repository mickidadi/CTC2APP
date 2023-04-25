package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
,
        foreignKeys = {
                @ForeignKey(entity = PatientEntity.class,
                        parentColumns = "PatientId",
                        childColumns = "PatientId",
                        onDelete = CASCADE, onUpdate = CASCADE)
        }
 */
@Entity(tableName = "tblVisits")
public class PatientVisitEntity {
        @PrimaryKey(autoGenerate = true)
        private int id;
        @ColumnInfo(name="PatientId")
        private String patientId;
        @ColumnInfo(name="VisitTypeCode")
        private String visitTypeCode;
        @ColumnInfo(name="VisitDate")
        private String visitDate;
        @ColumnInfo(name="NumDaysDispensed")
        private String numDaysDispensed;

        public PatientVisitEntity(String patientId, String visitTypeCode, String visitDate, String numDaysDispensed) {
                this.patientId = patientId;
                this.visitTypeCode = visitTypeCode;
                this.visitDate = visitDate;
                this.numDaysDispensed = numDaysDispensed;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getPatientId() {
                return patientId;
        }

        public void setPatientId(String patientId) {
                this.patientId = patientId;
        }

        public String getVisitTypeCode() {
                return visitTypeCode;
        }

        public void setVisitTypeCode(String visitTypeCode) {
                this.visitTypeCode = visitTypeCode;
        }

        public String getVisitDate() {
                return visitDate;
        }

        public void setVisitDate(String visitDate) {
                this.visitDate = visitDate;
        }

        public String getNumDaysDispensed() {
                return numDaysDispensed;
        }

        public void setNumDaysDispensed(String numDaysDispensed) {
                this.numDaysDispensed = numDaysDispensed;
        }
}
