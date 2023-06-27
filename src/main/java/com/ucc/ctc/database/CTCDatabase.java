package com.ucc.ctc.database;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ucc.ctc.dao.AdminHierarchyDao;
import com.ucc.ctc.dao.AdminHierarchyDivisionDao;
import com.ucc.ctc.dao.AdminHierarchyExtendedDao;
import com.ucc.ctc.dao.ClientBiometricDao;
import com.ucc.ctc.dao.ClientDao;
import com.ucc.ctc.dao.ClientPhysicalAddressDao;
import com.ucc.ctc.dao.ClientTreatmentSupporterDao;
import com.ucc.ctc.dao.FacilityDao;
import com.ucc.ctc.dao.PatientDao;
import com.ucc.ctc.dao.PatientVisitDao;
import com.ucc.ctc.dao.UserLoginDao;
import com.ucc.ctc.models.entity.AdminHierarchyDivisionEntity;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import com.ucc.ctc.models.entity.AdminHierarchyExtendedEntity;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.ClientPhysicalAddressEntity;
import com.ucc.ctc.models.entity.ClientTreatmentSupporterEntity;
import com.ucc.ctc.models.entity.FacilityEntity;
import com.ucc.ctc.models.entity.PatientEntity;
import com.ucc.ctc.models.entity.PatientVisitEntity;
import com.ucc.ctc.models.entity.UserEntity;


@Database(entities = {ClientEntity.class, FacilityEntity.class, UserEntity.class,
        PatientVisitEntity.class, PatientEntity.class, ClientBiometricEntity.class, ClientPhysicalAddressEntity.class,
          ClientTreatmentSupporterEntity.class, AdminHierarchyEntity.class,
        AdminHierarchyExtendedEntity.class, AdminHierarchyDivisionEntity.class},version =1)
public abstract class CTCDatabase extends RoomDatabase {

    private static CTCDatabase instance;

    public abstract ClientDao clientDao();
    public abstract PatientVisitDao patientVisitDao();
    public abstract FacilityDao facilityDao();
    public abstract UserLoginDao userLoginDao();
    public  abstract ClientBiometricDao clientBiometricDao();
    public abstract ClientPhysicalAddressDao clientPhysicalAddressDao();
    public abstract ClientTreatmentSupporterDao clientTreatmentSupporterDao();
    public abstract AdminHierarchyDao adminHierarchyDao();
    public abstract AdminHierarchyExtendedDao adminHierarchyExtendedDao();
    public abstract AdminHierarchyDivisionDao adminHierarchyDivisionDao();
    public abstract PatientDao patientDao();
    public static synchronized CTCDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            //If instance is null that's mean database is not created and create a new database instance
            instance = Room.databaseBuilder(context.getApplicationContext(),CTCDatabase.class,"ctc_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static Callback roomCallBack = new Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private FacilityDao facilityDao;

        public PopulateDbAsyncTask(CTCDatabase db) {

            facilityDao = db.facilityDao();
        }
      protected Void doInBackground(Void... voids) {
        //String facilityId, String CTCCode, String facilityName, String facilityType, String region, String council, String ward, String ctcWebUrl)
            facilityDao.insert(new FacilityEntity("120548-3","120548","KZB  CHIUNGUTWA","","","","",""));
            facilityDao.insert(new FacilityEntity("120547-5","120547","MIMIZ","","","","",""));
            facilityDao.insert(new FacilityEntity("120545-9","120545","MLIMANJIWA","","","","",""));
           return null;
      }

    }


}