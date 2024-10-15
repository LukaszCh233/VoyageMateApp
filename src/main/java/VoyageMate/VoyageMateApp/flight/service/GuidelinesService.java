package VoyageMate.VoyageMateApp.flight.service;

import VoyageMate.VoyageMateApp.flight.dto.GuidelinesDTO;
import VoyageMate.VoyageMateApp.flight.entity.Guidelines;
import VoyageMate.VoyageMateApp.flight.repository.GuidelinesRepository;
import VoyageMate.VoyageMateApp.mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuidelinesService {

    private final GuidelinesRepository guidelinesRepository;
    private final EntityMapper entityMapper;

    public GuidelinesService(GuidelinesRepository guidelinesRepository, EntityMapper entityMapper) {
        this.guidelinesRepository = guidelinesRepository;
        this.entityMapper = entityMapper;
    }

    public Guidelines saveUserGuidelines(Guidelines guidelines) {
        return guidelinesRepository.save(guidelines);
    }

    public GuidelinesDTO findUserGuidelines(Long id) {
        Guidelines guidelines = guidelinesRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Guidelines not found"));
        return entityMapper.mapGuideLinesToGuidelinesDTO(guidelines);
    }

    public List<GuidelinesDTO> findAllUserGuidelines() {
        List<Guidelines> guidelinesList = guidelinesRepository.findAll();
        if (guidelinesList.isEmpty()) {
            throw new EntityNotFoundException("Guidelines list is empty");
        }
        return entityMapper.mapGuideLinesListToGuidelinesListDTO(guidelinesList);

    }

    public void deleteUserGuidelinesById(Long id) {
        Guidelines guidelines = guidelinesRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Guidelines not found"));

        guidelinesRepository.delete(guidelines);
    }

    public void deleteAllGuidelines() {
        long count = guidelinesRepository.count();
        if (count == 0) {
            throw new EntityNotFoundException("Guidelines list is empty");
        }
        guidelinesRepository.deleteAll();
    }
}
