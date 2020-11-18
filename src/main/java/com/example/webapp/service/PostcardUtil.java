package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.PostcardBuilder;
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
                .setDateOfSend(LocalDate.parse(postcardDto.getDateOfSend()).atStartOfDay())
                .setDateOfReceive(LocalDate.parse(postcardDto.getDateOfReceive()).atStartOfDay())
                .setName(postcardDto.getName())
                .setConditionValue(postcardDto.getConditionValue())
                .setDistance(postcardDto.getDistance())
                .setDescription(postcardDto.getDescription())
                .getPostcard();
    }
}
