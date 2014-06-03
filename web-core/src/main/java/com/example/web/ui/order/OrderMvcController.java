package com.example.web.ui.order;

import com.example.common.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/03/2014
 * Time: 10:02
 */
@Controller
@SessionAttributes("userRoles")
public class OrderMvcController {

    @Logger
    org.slf4j.Logger log;


    OrderService orderService;

    @Autowired
    public OrderMvcController(OrderService orderService ) {
        this.orderService = orderService;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
      //  binder.setAllowedFields("addLine");
    //    binder.registerCustomEditor(String.class, "*price*", new CustomNumberEditor(BigDecimal.class, NumberFormat.getCurrencyInstance(Locale.UK), true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/customer/orders/{id}", method = RequestMethod.DELETE)
    public void cancelOrder(String id) { orderService.cancelOrder(id);}

    @RequestMapping(value = "/customer/orders/{id}", method = RequestMethod.GET)
    public Order find(@PathVariable String id) {
        return orderService.find(id);
    }


    @ModelAttribute("allOrders")
    public List<Order> populateOrders() {
        return orderService.findAll();

    }

    @RequestMapping({"/customer/orders"})
    public String initOrderForm(final Order order) {
        return "customer/orders";
    }



    @RequestMapping(value="/customer/orders", params={"save"})
    public String createOrder(Order order, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer/orders";
        }

        orderService.createOrder(order);

        return "redirect:/customer/orders";
    }



    @RequestMapping(value="/customer/orders", params={"addLine"})
    public String addLine(final Order order, final BindingResult bindingResult, final HttpServletResponse response ) {
        response.setHeader("Content-Type", "application/javascript; charset=utf-8");
        order.getLines().add(new OrderLine());
        return "customer/orders";
    }


    @RequestMapping(value="/customer/orders", params={"removeLine"})
    public String removeLine(final Order order, final BindingResult bindingResult, final HttpServletRequest req) {
        final int index = Integer.parseInt(req.getParameter("removeLine"));
        order.removeLine(index);
        return "customer/orders";
    }

}
