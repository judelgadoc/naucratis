package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.service.BookService;
import com.naucratis.naucratis.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;

@Controller
@RequestMapping("/estadisticas")
@SessionAttributes("role")
public class StatisticsController {

    @Autowired
    private PurchaseService ps;

    @Autowired
    private BookService bs;

    private List<Object[]> syys = new ArrayList<>(); // selected years yearly sales

    private List<Object[]> bsc = new ArrayList<>(); // result best selling categories

    private List<Object[]> bspm = new ArrayList<>(); // result book sellings per month

    private String selectedYearBestSellingCategories = "";

    private String selectedYearBookSellingPerMonth = "";

    private long selectedIsbnBookSellingPerMonth = 0;

    @GetMapping
    public String estadisticas(Model model) {

        List<Integer> years = ps.getYears();
        List<Book> books = bs.findAll();

        List<Integer> labelsYearlySales = new ArrayList<>();
        List<Double> valuesYearlySales = new ArrayList<>();

        List<String> labelsBestSellingCategories = new ArrayList<>();
        List<Double> valuesBestSellingCategories = new ArrayList<>();

        List<BigInteger> labelsBookSellingPerMonth = new ArrayList<>();
        List<Double> valuesBookSellingPerMonth = new ArrayList<>();

        for (Object[] x : syys) {
            labelsYearlySales.add((int) x[0]);
            valuesYearlySales.add((double)x[1]);
        }

        for (Object[] x : bsc) {
            labelsBestSellingCategories.add( (String) x[0]);
            valuesBestSellingCategories.add( (double) x[1]);
        }

        for (Object[] x : bspm) {
            System.out.println(x[0].getClass());
            labelsBookSellingPerMonth.add( (BigInteger) x[0]);
            valuesBookSellingPerMonth.add( (double) x[1]);
        }

        model.addAttribute("years", years);
        model.addAttribute("books", books);

        model.addAttribute("labelsYearlySales", labelsYearlySales);
        model.addAttribute("valsYearlySales", valuesYearlySales);
        model.addAttribute("labelsBookSellingPerMonth", labelsBookSellingPerMonth);
        model.addAttribute("valsBookSellingPerMonth", valuesBookSellingPerMonth);
        model.addAttribute("labelsBestSellingCategories", labelsBestSellingCategories);
        model.addAttribute("valsBestSellingCategories", valuesBestSellingCategories);

        model.addAttribute("selectedYearBestSellingCategories", selectedYearBestSellingCategories);
        model.addAttribute("selectedYearBookSellingPerMonth", selectedYearBookSellingPerMonth);
        model.addAttribute("selectedIsbnBookSellingPerMonth", selectedIsbnBookSellingPerMonth);

        return "estadisticas";
    }

    @PostMapping("/show1")
    public String showYearlySales(Model model, @RequestParam("selectedYearsYearlySales") String[] selectedYearsYearlySales) {
        syys.clear();
        for (String x : selectedYearsYearlySales)
            syys.add(ps.getSalesIn((Integer.parseInt(x))).get(0));
        return "redirect:/estadisticas";
    }

    @PostMapping("/show2")
    public String showBookSellingPerMonth(Model model, @RequestParam("selectedBookSellingPerMonth") String selectedBookSellingPerMonth,
                                          @RequestParam("selectedBookIsbn") String selectedIsbnBookSellingPerMonth) {
        selectedYearBookSellingPerMonth = selectedBookSellingPerMonth;
        this.selectedIsbnBookSellingPerMonth = Long.parseLong(selectedIsbnBookSellingPerMonth);
        bspm = ps.getBookSalesPerMonth(Integer.parseInt(selectedYearBookSellingPerMonth), this.selectedIsbnBookSellingPerMonth);
        return "redirect:/estadisticas";
    }

    @PostMapping("/show3")
    public String showBestSellingCategories(Model model, @RequestParam("selectedBestSellingCategories") String selectedBestSellingCategories) {
        selectedYearBestSellingCategories = selectedBestSellingCategories;
        bsc = ps.getSalesCategories(Integer.parseInt(selectedYearBestSellingCategories));
        return "redirect:/estadisticas";
    }

}