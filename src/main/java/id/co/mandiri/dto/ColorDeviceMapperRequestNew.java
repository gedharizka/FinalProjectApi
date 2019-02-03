package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.ColorDevice;
import org.mapstruct.factory.Mappers;

public interface ColorDeviceMapperRequestNew extends ObjectMapper<ColorDevice, ColorDeviceDTO.ColorDeviceRequestNewDTO> {

    ColorDeviceMapperRequestNew converter = Mappers.getMapper(ColorDeviceMapperRequestNew.class);
}
