package com.backend.santasworkshopbackend.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.santasworkshopbackend.entity.User;
import com.backend.santasworkshopbackend.dto.UserDTO;
import com.backend.santasworkshopbackend.entity.Toy;
import com.backend.santasworkshopbackend.dto.ToyDTO;
import com.backend.santasworkshopbackend.entity.Child;
import com.backend.santasworkshopbackend.dto.ChildDTO;
import com.backend.santasworkshopbackend.entity.Delivery;
import com.backend.santasworkshopbackend.dto.DeliveryDTO;

@Configuration
public class Mapper {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setRole(source.getRole().getId()); // or getRoleDescription(), depending on what you want
            }
        });

        modelMapper.addMappings(new PropertyMap<Toy, ToyDTO>() {
            @Override
            protected void configure() {
                map().setDescriptionID(source.getDescription().getId());
                map().setAddedById(source.getAddedBy().getId());
                map().setUpdatedById(source.getUpdatedBy().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Child, ChildDTO>() {
            @Override
            protected void configure() {
                map().setStatusID(source.getStatusID().getId());
                map().setLocationID(source.getLocationID().getId());
            }
        });
        
        modelMapper.addMappings(new PropertyMap<Delivery, DeliveryDTO>() {
            @Override
            protected void configure() {
                map().setChildID(source.getChildID().getId());
                map().setLocationID(source.getLocationID().getId());
                map().setToyID(source.getToyID().getId());
                map().setStatusID(source.getStatusID().getId());
            }
        });

        return modelMapper;
    }
}