package ua.kpi.its.desktopcloud.database.utils;

import ua.kpi.its.desktopcloud.database.dao.SupplicantDAO;
import ua.kpi.its.desktopcloud.database.entity.Supplicant;

import java.io.IOException;
import java.util.List;

/**
 * Created by rad1kal on 27.04.14.
 */
public class SupplicantUtils {
    private static SupplicantDAO dao;


    static {
        try {
            dao = new SupplicantDAO();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void limitBandwidthForAllSupplicants(){
        List<Supplicant> supplicants = dao.getAll();

        for (Supplicant supplicant : supplicants){

        }
    }
}
