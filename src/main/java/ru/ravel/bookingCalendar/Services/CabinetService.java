package ru.ravel.bookingCalendar.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ravel.bookingCalendar.DAO.CabinetDAO;

@Service
public class CabinetService {

    @Autowired
    private CabinetDAO cabinetDAO;

    public long getCabinetIdByNumber(long cabinetNumber){
        return cabinetDAO.getCabinetIdByNumber(cabinetNumber);
    }

}
