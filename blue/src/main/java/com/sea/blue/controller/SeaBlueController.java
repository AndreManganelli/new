package com.sea.blue.controller;

import com.sea.blue.model.SeaBlueProduct;
import com.sea.blue.model.Vaccine;
import com.sea.blue.servcie.SeaBlueService;
import com.sea.blue.servcie.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SeaBlueController
{
    @Autowired
    private SeaBlueService seaBlueService;

    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/home")
    public String home(HttpServletRequest request, HttpServletResponse resp ,Model model)
    {
        StringBuffer pathInfo = request.getRequestURL();
        model.addAttribute("seaBlueUrl", pathInfo);
        return "home";
    }

    /**
     * First Page
     */
    @GetMapping("/showAllSeaBlue")
    public String showAllSeaBlue(Model model)
    {
        model.addAttribute("herdList", seaBlueService.getAllHerdsList());

        addAllDate(model);
        addSeaBlueProductAttribute(model);

        return "seaBluePage";
    }

    @RequestMapping(value = "/searchSeaBlueProductByDate" , method= RequestMethod.POST)
    public String searchSeaBlueProductByDate(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        model.addAttribute("herdList", seaBlueService.getAllHerdsList());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "seaBluePage";
    }

    @RequestMapping(value = "/saveSeaBlue" , method= RequestMethod.POST)
    public String saveSeaBlue(@ModelAttribute SeaBlueProduct seaBlueProduct, Model model)
    {
        saveSeaBlueProduct(seaBlueProduct);
        model.addAttribute("herdList", seaBlueService.getAllHerdsList());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "seaBluePage";
    }

    /**
     * Vaccine Page
     */
    @GetMapping("/showSubmitForm")
    public String showSubmitForm(Model model)
    {
        List<Vaccine> vaccines  = vaccineService.getAllVaccineList();
        model.addAttribute("vaccines", vaccines);
        return "showSubmitForm";
    }

    /**
     * Feed Page day
     */
    @GetMapping("/DisplayedDayFeed")
    public String displayedDayFeed(Model model) {
        // add model attribute seaBlueProduct last input
        addSeaBlueProductAttribute(model);

        model.addAttribute("dateList", seaBlueService.getAllDateList());
        model.addAttribute("feedList", seaBlueService.getAllFeedList());
        return "dayFeed";
    }

    @RequestMapping(value = "/searchSeaBlueProductByFeed" , method= RequestMethod.POST)
    public String searchSeaBlueProductByFeed(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        model.addAttribute("feedList", seaBlueService.getAllFeedList());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "dayFeed";
    }

    @RequestMapping(value = "/saveSeaBlueProductByFeed" , method= RequestMethod.POST)
    public String saveSeaBlueProductByFeed(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        saveSeaBlueProduct(seaBlueProduct);
        model.addAttribute("feedList", seaBlueService.getAllFeedList());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "dayFeed";
    }

    /**
     * feed total
     */
    @GetMapping("/DisplayedDayFeedTotal")
    public String displayedDayFeedTotal(Model model) {
        // add model attribute seaBlueProduct last input
        addSeaBlueProductAttribute(model);

        model.addAttribute("dateList", seaBlueService.getAllDateList());
        model.addAttribute("feedList", seaBlueService.getAllFeedTotalList());
        return "dayFeedTotal";
    }

    @RequestMapping(value = "/searchSeaBlueProductByFeedTotal" , method= RequestMethod.POST)
    public String searchSeaBlueProductByFeedTotal(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        model.addAttribute("feedList", seaBlueService.getAllFeedList());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "dayFeedTotal";
    }

    @RequestMapping(value = "/saveSeaBlueProductByTotalFeed" , method= RequestMethod.POST)
    public String saveSeaBlueProductByTotalFeed(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        saveSeaBlueProduct(seaBlueProduct);
        model.addAttribute("feedList", seaBlueService.getAllFeedList());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "dayFeedTotal";
    }

    /**
     * Egg Produce
     */
    @GetMapping("/EggProduceRate")
    public String EggProduceRate(Model model) {
        // add model attribute seaBlueProduct last input
        addSeaBlueProductAttribute(model);

        model.addAttribute("dateList", seaBlueService.getAllDateList());
        model.addAttribute("displayList", seaBlueService.getAllEggProduceRate());
        return "EggProduceRate";
    }

    @RequestMapping(value = "/searchSeaBlueProductByEggProduceRate" , method= RequestMethod.POST)
    public String searchSeaBlueProductByEggProduceRate(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        model.addAttribute("displayList", seaBlueService.getAllEggProduceRate());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "EggProduceRate";
    }

    @RequestMapping(value = "/saveSeaBlueProductByEggProduceRate" , method= RequestMethod.POST)
    public String saveSeaBlueProductByEggProduceRate(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        saveSeaBlueProduct(seaBlueProduct);
        model.addAttribute("displayList", seaBlueService.getAllFeedList());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "EggProduceRate";
    }

    /**
     * week out rate
     */
    @GetMapping("/WeekOutNumber")
    public String WeekOutNumber(Model model) {
        // add model attribute seaBlueProduct last input
        addSeaBlueProductAttribute(model);

        model.addAttribute("dateList", seaBlueService.getAllDateList());
        model.addAttribute("displayList", seaBlueService.getAllWeekOutNumberList());
        return "weekOutNumber";
    }

    @RequestMapping(value = "/searchSeaBlueProductByWeekOutNumber" , method= RequestMethod.POST)
    public String searchSeaBlueProductByWeekOutNumber(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        model.addAttribute("displayList", seaBlueService.getAllEggProduceRate());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "weekOutNumber";
    }

    @RequestMapping(value = "/saveSeaBlueProductByWeekOutNumber" , method= RequestMethod.POST)
    public String saveSeaBlueProductByWeekOutNumber(@ModelAttribute(value="seaBlueDate")SeaBlueProduct seaBlueProduct, Model model)
    {
        saveSeaBlueProduct(seaBlueProduct);
        model.addAttribute("displayList", seaBlueService.getAllFeedList());

        addAllDate(model);
        searchSeaBlueByDate(seaBlueProduct, model);

        return  "weekOutNumber";
    }

    /**
     * x -> type y -> price ( hreds * number of the day)
     */
    @GetMapping("/displayedTotalVaccinePrice")
    public String displayedTotalVaccinePrice(Model model) {
        List<Vaccine> vaccineList = displayVaccineTotalPrice(model);

        model.addAttribute("vaccine", vaccineList.get(vaccineList.size()-1));

        return "vaccinePrice";
    }

    @RequestMapping(value = "/searchVaccineByStep" , method= RequestMethod.POST)
    public String searchVaccineByStep(@ModelAttribute Vaccine vaccine, Model model)
    {
        model.addAttribute("vaccine",vaccineService.findByStep(vaccine.getProcedureNumber()));
        displayVaccineTotalPrice(model);
        return  "vaccinePrice";
    }

    @RequestMapping(value = "/saveVaccinePrince", method = RequestMethod.POST)
    public String saveVaccinePrince(@ModelAttribute Vaccine vaccine, Model model) {
        vaccineService.saveVaccine(vaccine);

        model.addAttribute("vaccine",vaccineService.findByStep(vaccine.getProcedureNumber()));
        displayVaccineTotalPrice(model);
        return "vaccinePrice";
    }

    @RequestMapping(value = "/deleteVaccine", method = RequestMethod.POST)
    public String deleteVaccine(@ModelAttribute Vaccine vaccine, Model model) {
        vaccineService.deleteVaccine(vaccine);

        model.addAttribute("vaccine",vaccineService.findByStep(vaccine.getProcedureNumber()));
        displayVaccineTotalPrice(model);
        return "vaccinePrice";
    }


    private List<Vaccine> displayVaccineTotalPrice(Model model) {
        //get all type of vaccine
        List<Vaccine> vaccineList = vaccineService.getAllVaccineList();
        //get day age by vaccine type
        List<SeaBlueProduct> seaBlueProductList = new LinkedList<>();
        vaccineList.forEach(vaccine -> {
            //get herds by day age
            seaBlueProductList.add(seaBlueService.findByDayAge(vaccine.getDayAge()));
        });


        List<String> typeList = new LinkedList();
        List<String> totalPriceList = new LinkedList<>();
        //get price by herds * vaccine type price
        for (int i = 0; i < vaccineList.size(); i++) {
            String price = vaccineList.get(i).getPrice();
            SeaBlueProduct seaBlueProduct = seaBlueProductList.get(i);
            if (seaBlueProduct == null || StringUtils.isEmpty(price)) {
                typeList.add(vaccineList.get(i).getType());
                totalPriceList.add("" + 0);
                continue;
            }

            String herds = seaBlueProduct.getHerds();
            if (StringUtils.isEmpty(herds)) {
                typeList.add(vaccineList.get(i).getType());
                totalPriceList.add("" + 0);
                continue;
            }
            String totalPrice = Double.parseDouble(price) * Double.parseDouble(herds) + "";
            typeList.add(vaccineList.get(i).getType());
            totalPriceList.add(totalPrice);
        }

        model.addAttribute("typeList", typeList);
        model.addAttribute("totalPriceList", totalPriceList);
        return vaccineList;
    }


    private void addSeaBlueProductAttribute(Model model) {
        List<SeaBlueProduct> allSeaBlue = seaBlueService.getAllSeaBlue();
        model.addAttribute("seaBlueProduct", allSeaBlue.get(allSeaBlue.size()-1));
    }

    private void addAllDate(Model model) {
        List<String> dateList = seaBlueService.getAllDateList();
        model.addAttribute("dateList", dateList);
    }

    private void searchSeaBlueByDate(SeaBlueProduct seaBlueProduct, Model model) {
        if(seaBlueProduct == null)
            return;
        String date = seaBlueProduct.getDate().trim();
        model.addAttribute("seaBlueProduct", seaBlueService.findByDate(date));
    }

    private void saveSeaBlueProduct(SeaBlueProduct seaBlueProduct) {
        seaBlueService.saveSeaBlueProduct(seaBlueProduct);
    }
}
