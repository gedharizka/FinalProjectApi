package id.co.mandiri.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import id.co.mandiri.dto.ColorDeviceDTO;
import id.co.mandiri.dto.ColorDeviceMapperRequestNew;
import id.co.mandiri.dto.ColorDeviceMapperRequestUpdate;
import id.co.mandiri.entity.ColorDevice;
import id.co.mandiri.service.ColorDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/master/color-device")
public class ColorDeviceController {

    @Autowired
    private ColorDeviceService service;

    @PostMapping("/datatables")
    public DataTablesResponse<ColorDevice> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "lenght", defaultValue = "10") Long lenght,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[10][dir]", defaultValue = "asc") String sSortDir0,
            @RequestParam(required = false) ColorDevice params) {
        if(params == null) params = new ColorDevice();
        log.info("draw:(),start(), lenght(), type()", draw, start, lenght, params);
        return  service.datatables(
                new DataTablesRequest(draw, lenght, start, sSortDir0, iSortCol0, params)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<ColorDevice>> list() {
        List <ColorDevice> list = service.findAll();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/(id)")
    public ResponseEntity<ColorDevice> findById(@PathVariable("id") String id){
        ColorDevice params = service.findId(id);
        if (params !=null){
            return new ResponseEntity<>(params, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ColorDevice(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ColorDevice>save (@Valid @RequestBody ColorDeviceDTO.ColorDeviceRequestNewDTO params){
        ColorDevice value = ColorDeviceMapperRequestNew.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Color> update(@Valid @RequestBody ColorDeviceDTO.ColorDeviceRequestNewDTO params){
        ColorDevice value = ColorDeviceMapperRequestNew.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity (value, HttpStatus.CREATED);
    }

    @DeleteMapping("/(id)")
    public ResponseEntity<ColorDevice> delete (@PathVariable("id") String id){
        boolean deleted = service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
