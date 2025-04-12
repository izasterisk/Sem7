/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.managers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import models.Customer;
import models.Flower;
import models.Order;
import tools.FileHandle;
import tools.InputHandle;
import tools.PrintingFormat;

/**
 *
 * @author Administrator
 */
public class OrderManager extends HashSet<Order> {

    public OrderManager(String path, FlowerManager fm) {
        ArrayList<String> dta = FileHandle.readFromFile(path);
        for (String line : dta) {
            if (line == null || line.isEmpty()) {
                continue;
            }
            String lineSplit[] = line.trim().split(",");
            Order o = new Order(lineSplit[0],
                    InputHandle.toDate(lineSplit[1], "yyyy/MM/dd"),
                    lineSplit[3]);
            int count = 0;
            for (String dataStr : lineSplit) {
                if (count > 3) {
                    String _data[] = dataStr.split(":");
                    Flower f = fm.find(_data[0]);
                    int quant = Integer.parseInt(_data[1]);
                    o.addFlower(f, quant);
                }
                count++;
            }
            this.add(o);
        }
    }

    private String autoTakingID() {
        String id = "O";
        String num = Integer.toString(this.size());
        for (int i = 0; i < 3 - num.length(); i++) {
            id += "0";
        }
        id += num;
        return id;
    }

    public Order find(String id) {
        for (Order o : this) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }

    public Order newOrder(String buyerId) {
        String id = autoTakingID();
        Date date = new Date();
        Order o = new Order(id, date, buyerId);
        return o;
    }

    public void removeOrder() {
        String id;
        while (true) {
            id = InputHandle.getString("Enter flower id OXXX: ");
            if (id.matches("O\\d{3}") && find(id) != null) {
                break;
            }
        }
        Order o = find(id);
        System.out.println("Successfully remove");
        this.remove(o);
    }
//like

    public void showList(CustomerManager cm) {
        String sOrd, tOrd;
        String fDate, tDate;
        while (true) {
            sOrd = InputHandle.getString("Enter sort term (count, date or price):");
            if (sOrd.equals("count") || sOrd.equals("date") || sOrd.equals("price")) {
                break;
            }
        }
        while (true) {
            tOrd = InputHandle.getString("Enter type (asc, desc):");
            if (tOrd.equals("asc") || tOrd.equals("desc")) {
                break;
            }
        }
        fDate = InputHandle.getString("Enter from Date (You can ignore by just enter):");
        tDate = InputHandle.getString("Enter to Date (You can ignore by just enter):");
        Set<Order> om = sort(sOrd, tOrd);
        PrintingFormat.printLine120();
        String title = PrintingFormat.print6Args("No.", "Order Id", "Order Date", "Customer", "Flower Count", "Total");
        System.out.println(title);
        PrintingFormat.printLine120();
        int count = 0;
        double total = 0;
        Date sDate = fDate.isEmpty() ? null : InputHandle.toDate(fDate, "yyyy/MM/dd");
        Date eDate = tDate.isEmpty() ? null : InputHandle.toDate(tDate, "yyyy/MM/dd");
        for (Order o : om) {
            if (!inDateRange(sDate, eDate, o)) {
                continue;
            }
            Customer c = cm.find(o.getBuyerId());
            String line = PrintingFormat.print6Args(
                    Integer.toString(count),
                    o.getId(),
                    InputHandle.toDateString(o.getDate(), "yyyy/MM/dd"),
                    c.getName(),
                    Integer.toString(o.getFlowerCount()),
                    String.format("%.2f",o.getTotal()));
            total += o.getTotal();
            System.out.println(line);
            count++;
        }
        PrintingFormat.printLine120();
        String _total = PrintingFormat.printTotal120(String.format("Total: %.2f", total));
        System.out.println(_total);
        PrintingFormat.printLine120();
    }

    private boolean inDateRange(Date sDate, Date eDate, Order o) {
        if (sDate == null && eDate == null) {
            return true;
        } else if (sDate == null) {
            Date date = o.getDate();
            return date.equals(sDate) || date.after(sDate);
        } else if (eDate == null) {
            Date date = o.getDate();
            return date.equals(eDate) || date.before(sDate);
        } else {
            Date date = o.getDate();
            boolean isBefore = date.equals(eDate) || date.before(sDate);
            boolean isAfter = date.equals(sDate) || date.after(sDate);
            return isAfter && isBefore;
        }
    }

    private Set<Order> sort(String sOrd, String tOrd) {
        Set<Order> ret = new TreeSet<>();
        if (sOrd.equals("count")) {
            ret = tOrd.equals("asc") ? new TreeSet<>(new CmpCount()) : new TreeSet<>(new CmpCount().reversed()) ;
        } else if (sOrd.equals("date")) {
            ret = tOrd.equals("asc") ? new TreeSet<>(new CmpDate()) : new TreeSet<>(new CmpDate().reversed()) ;
        } else {
            ret = tOrd.equals("asc") ? new TreeSet<>(new CmpTotal()) : new TreeSet<>(new CmpTotal().reversed()) ;
        }
        for (Order o : this) {
            ret.add(o);
        }
        return ret;
    }

    public ArrayList<String> toArrayList() {
        ArrayList<String> ret = new ArrayList<>();
        for (Order s : this) {
            ret.add(s.toString());
        }
        return ret;
    }
}

class CmpDate implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getDate().equals(o2.getDate())) {
            return o1.getId().compareTo(o2.getId());
        } else {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

}

class CmpCount implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (Integer.compare(o1.getFlowerCount(), o2.getFlowerCount()) == 0) {
            return o1.getId().compareTo(o2.getId());
        } else {
            return Integer.compare(o1.getFlowerCount(), o2.getFlowerCount());
        }
    }

}

class CmpTotal implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (Double.compare(o1.getTotal(), o2.getTotal()) == 0) {
            return o1.getId().compareTo(o2.getId());
        } else {
            return Double.compare(o1.getTotal(), o2.getTotal());
        }
    }

}
