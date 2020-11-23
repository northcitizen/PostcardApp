package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.dto.UserDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.PostcardBuilder;
import com.example.webapp.model.User;
import com.example.webapp.model.UserBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PostcardUtil {

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static ModelMapper modelMapper = new ModelMapper();

    /**
     * Model mapper property setting are specified in the following block.
     * Default property matching strategy is set to Strict see {@link MatchingStrategies}
     * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
     */
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Hide from public usage.
     */
    private PostcardUtil() {
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param <D>      type of result object.
     * @param <T>      type of source object to map from.
     * @param entity   entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     */
    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param entityList list of entities that needs to be mapped
     * @param outCLass   class of result list element
     * @param <D>        type of objects in result list
     * @param <T>        type of entity in <code>entityList</code>
     * @return list of mapped object with <code><D></code> type.
     */
    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public static Postcard DtoToPostcard(PostcardDto postcardDto) {
        return new PostcardBuilder()
                .setId(postcardDto.getId())
                .setPostNumber(postcardDto.getPostNumber())
                .setCountry(postcardDto.getCountry())
                .setReceiveDate(LocalDate.parse(postcardDto.getSendDate()).atStartOfDay())
                .setSendDate(LocalDate.parse(postcardDto.getReceiveDate()).atStartOfDay())
                .setName(postcardDto.getName())
                .setStatus(postcardDto.getStatus())
                .setDistance(postcardDto.getDistance())
                .setDescription(postcardDto.getDescription())
                .setUser(postcardDto.getUser())
                .getPostcard();
    }

    public static User DtoToUser(UserDto userDto) {
        return new UserBuilder()
                .setId(userDto.getId())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .getUser();
    }

//    public static PostcardDto postcardToDto(Postcard postcard) {
//        return new PostcardDto.Builder(postcard.getId(), postcard.getPostNumber(),
//                postcard.getCountry(),
//                postcard.getSendDate().toString(),
//                postcard.getReceiveDate().toString())
//                .name(postcard.getName())
//                .status(postcard.getStatus())
//                .distance(postcard.getDistance())
//                .description(postcard.getDescription())
//                .build();
//    }
}