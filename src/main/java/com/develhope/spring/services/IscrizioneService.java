package com.develhope.spring.services;

import com.develhope.spring.DAO.CourseDAO;
import com.develhope.spring.DAO.IscrizioneDAO;
import com.develhope.spring.DAO.UserDAO;
import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.Iscrizione;
import com.develhope.spring.entities.User;
import com.develhope.spring.exceptions.GradeException;
import com.develhope.spring.exceptions.IscrizioneException;
import com.develhope.spring.mappers.IscrizioneMapper;
import com.develhope.spring.mappers.UserMapper;
import com.develhope.spring.models.DTO.CourseDTO;
import com.develhope.spring.models.DTO.IscrizioneDTO;
import com.develhope.spring.models.DTO.UserDTO;
import com.develhope.spring.validators.IscrizioneValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IscrizioneService {
    @Autowired
    private IscrizioneDAO iscrizioneDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private IscrizioneValidator iscrizioneValidator;
    @Autowired
    private IscrizioneMapper iscrizioneMapper;
    @Autowired
    private UserMapper userMapper;

    public IscrizioneDTO subscribeToCourse(Long idUser, Long idCourse) throws IscrizioneException {
        User user = userDAO.findById(idUser).orElse(null);
        Course course = courseDAO.findById(idCourse).orElse(null);
        if(iscrizioneValidator.isIscrizioneValid(user,course)){
            Iscrizione iscrizione = new Iscrizione();
            iscrizione.setCourse(course);
            iscrizione.setUser(user);
            Date dataIscrizione = new Date();
            iscrizione.setDataIscrizione(dataIscrizione.toString());
            iscrizione.setPayed(false);
            Iscrizione saved = iscrizioneDAO.saveAndFlush(iscrizione);
            return iscrizioneMapper.entityToDTO(saved);
        }else{
            throw new IscrizioneException("Subscription not added, a problem occurred with the data", 400);
        }
    }
    public IscrizioneDTO getById(Long id) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElse(null);
        if(iscrizione!=null){
            return iscrizioneMapper.entityToDTO(iscrizione);
        }else{
            throw new IscrizioneException("Subscription not found", 404);
        }
    }
    public List<IscrizioneDTO> getAll() {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findAll();
        List<IscrizioneDTO> iscrizioneDTOList = new ArrayList<>();
        for (Iscrizione iscrizione:iscrizioneList) {
            iscrizioneDTOList.add(iscrizioneMapper.entityToDTO(iscrizione));
        }
            return iscrizioneDTOList;
    }

    public IscrizioneDTO payedSwitch(Long id) throws IscrizioneException {
        Iscrizione iscrizione = iscrizioneDAO.findById(id).orElse(null);
        if(iscrizione!= null) {
            iscrizione.setPayed(!iscrizione.getPayed());
            Iscrizione saved = iscrizioneDAO.saveAndFlush(iscrizione);
            return iscrizioneMapper.entityToDTO(saved);
        }else{
            throw new IscrizioneException("Subscription not found", 404);
        }
    }

    public void deleteSubscription(Long id) throws IscrizioneException {
        if(iscrizioneDAO.existsById(id)){
            iscrizioneDAO.deleteById(id);
        }else{
            throw new IscrizioneException("Subscription not found", 404);
        }
    }

    public List<IscrizioneDTO> getAllByTutor(Long id) throws IscrizioneException {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findAll();
        List<IscrizioneDTO> iscrizioneDTOList = new ArrayList<>();
        for (Iscrizione iscrizione:iscrizioneList) {
            if(iscrizione.getCourse().getTutor().getId() == id) {
                iscrizioneDTOList.add(iscrizioneMapper.entityToDTO(iscrizione));
            }
        }
        if(!iscrizioneList.isEmpty()) {
            return iscrizioneDTOList;
        }else {
            throw new IscrizioneException("Subscription not found", 400);
        }
    }

    public List<UserDTO> getUserByCourse(Long id) throws IscrizioneException {
        List<Iscrizione> iscrizioneList = iscrizioneDAO.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Iscrizione iscrizione:iscrizioneList) {
            if(iscrizione.getCourse().getId() == id) {
                userDTOList.add(userMapper.entityToDto(iscrizione.getUser()));
            }
        }
        if(!userDTOList.isEmpty()) {
            return userDTOList;
        }else {
            throw new IscrizioneException("No Client found", 400);
        }
    }
}
