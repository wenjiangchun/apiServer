package com.dlyy.api.park.web;

import com.dlyy.api.park.entity.ParkSystem;
import com.dlyy.api.park.service.ParkSystemService;
import com.dlyy.api.park.util.ParkBrand;
import com.dlyy.api.web.base.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/api/park")
public class ParkController extends BaseCrudController<ParkSystem, Long> {

    private ParkSystemService parkSystemService;

    public ParkController(ParkSystemService parkSystemService) {
        super("api", "parkSystem", "停车系统", parkSystemService);
        this.parkSystemService = parkSystemService;
    }

    @Override
    protected void setPageQueryVariables(Map<String, Object> queryVariables, HttpServletRequest request) {

    }

    @Override
    protected void setModel(Model model, HttpServletRequest request) {
        super.setModel(model, request);
        model.addAttribute("parkBrandList", ParkBrand.values());
    }

}