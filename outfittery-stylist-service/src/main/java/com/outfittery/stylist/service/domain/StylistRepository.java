package com.outfittery.stylist.service.domain;

import org.springframework.data.repository.CrudRepository;
import com.outfittery.stylist.service.domain.Stylist;

public interface StylistRepository extends CrudRepository<Stylist, Long>
{
    Stylist findById(long stylistId);
}
