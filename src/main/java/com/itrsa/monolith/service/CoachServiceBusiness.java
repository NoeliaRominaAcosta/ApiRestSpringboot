package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.CoachDTO;
import com.itrsa.monolith.dto.EmployeeDTO;
import com.itrsa.monolith.entity.Coach;
import com.itrsa.monolith.mapper.CoachMapper;
import com.itrsa.monolith.mapper.EmployeeMapper;
import com.itrsa.monolith.repository.CoachRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Component
public class CoachServiceBusiness implements CoachService{

    private final CoachRepository coachRepository;

    public CoachServiceBusiness (CoachRepository coachRepository){
        this.coachRepository = coachRepository;
    }

    @Override
    public Iterable<CoachDTO> findAll() {
        var mapper = Mappers.getMapper(CoachMapper.class);
        return mapper.toList(coachRepository.findAll());
    }

    @Override
    public CoachDTO findByDni(String dni) {
        var mapper = Mappers.getMapper(CoachMapper.class);
        return mapper.toCoachDTO(coachRepository.findByDni(dni));
    }


    @Override
    public void save(CoachDTO dto) {
        var mapper = Mappers.getMapper(CoachMapper.class);
        coachRepository.save(mapper.toCoach(dto));
    }


    @Override
    public void update(CoachDTO dto) {
        String dni = dto.getDni();

        Coach entity = coachRepository.findByDni(dni);

        entity.setName(dto.getName());
        entity.setLastname(dto.getLastname());
        entity.setRole(dto.getRole());
        entity.setShortGoal(dto.getShortGoal());
        entity.setLongGoal(dto.getLongGoal());

        coachRepository.save(entity);
    }

    @Override
    public void deleteByDni(String dni) {

        Coach coach = coachRepository.findByDni(dni);

        coachRepository.delete(coach);
    }

    @Override
    public Iterable<EmployeeDTO> listEmployees(String dni) {
        Coach coach = coachRepository.findByDni(dni);
        var mapper = Mappers.getMapper(EmployeeMapper.class);

        return mapper.toList(coach.getEmployees());
    }


}
