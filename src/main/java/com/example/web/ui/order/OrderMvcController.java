package com.example.web.ui.order;

import com.example.common.logging.Loggable;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Loggable
    Logger log;

    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void cancelOrder(String id) { orderService.cancelOrder(id);}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order find(@PathVariable String id) {
        return orderService.find(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Order> findAll() {
        return orderService.findAll();

    }

    @RequestMapping({"/customer/orders"})
    public String showOrder(final Order order) {
        return "customer/orders";
    }



    @RequestMapping(value="/customer/orders", params={"save"})
    public String createOrder(final Order order, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            return "customer/orders";
        }
        orderService.createOrder(order);

        return "redirect:/customer/orders";
    }



    @RequestMapping(value="/customer/orders", params={"addRow"})
    public String addRow(final Order order, final BindingResult bindingResult) {
        order.addLine(new OrderLine());
        return "customer/orders";
    }


    @RequestMapping(value="/customer/orders", params={"removeRow"})
    public String removeRow(final Order order, final BindingResult bindingResult, final HttpServletRequest req) {
        final String rowId = req.getParameter("removeRow");
        order.getLines().remove(rowId);
        return "customer/orders";
    }

}
