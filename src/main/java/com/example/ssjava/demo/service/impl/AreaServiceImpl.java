package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.entity.Area;
import com.example.ssjava.demo.repository.AreaRepository;
import com.example.ssjava.demo.service.AreaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AreaServiceImpl implements AreaService {

  private final AreaRepository areaRepository;

  @Override
  public List<Area> findAllAreas() {
    return areaRepository.findAll();
  }

  @Override
  public Optional<Area> findAreaById(Integer id) {
    return areaRepository.findById(id);
  }

  @Override
  public Area createArea(Area area) {
    return areaRepository.save(area);
  }

  @Override
  public Area updateArea(Area area) {
    return areaRepository.save(area);
  }

  @Override
  public void deleteArea(Integer id) {
    areaRepository.deleteById(id);
  }
}