package com.example.ssjava.demo.service;

import com.example.ssjava.demo.entity.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {
  List<Area> findAllAreas();
  Optional<Area> findAreaById(Integer id);
  Area createArea(Area area);
  Area updateArea(Area area);
  void deleteArea(Integer id);
}