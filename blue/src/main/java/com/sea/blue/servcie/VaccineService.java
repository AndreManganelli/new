package com.sea.blue.servcie;

import com.sea.blue.dao.VaccineRepo;
import com.sea.blue.model.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService
{
    @Autowired
    private VaccineRepo vaccineRepo;

    public List<Vaccine> getAllVaccineList()
    {
        return (List<Vaccine>) vaccineRepo.findAll();
    }

    public Vaccine findByStep(String procedureNumber) {
        List<Vaccine> vaccineList = vaccineRepo.findByStep(procedureNumber);
        if(vaccineList == null || vaccineList.size() == 0)
            return new Vaccine();
        return vaccineList.get(0);
    }

    public void saveVaccine(Vaccine vaccine) {
        vaccineRepo.save(vaccine);
    }

    public Vaccine findByID(long id) {
        return Optional.ofNullable(vaccineRepo.findById((int)id).get()).orElse(new Vaccine());
    }

    public void deleteVaccine(Vaccine vaccine) {
        vaccineRepo.delete(vaccine);
    }
}
