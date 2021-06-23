package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.library.Book;
import com.naucratis.naucratis.model.library.CopyBook;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.model.transaction.Order;
import com.naucratis.naucratis.model.transaction.Purchase;
import com.naucratis.naucratis.model.transaction.Sale;
import com.naucratis.naucratis.model.transaction.Status;
import com.naucratis.naucratis.model.user.Customer;
import com.naucratis.naucratis.model.user.User;
import com.naucratis.naucratis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository     userRepository;
    @Autowired
    private CopyBookRepository copyBookRepository;
    @Autowired
    private LibraryRepository  libraryRepository;
    @Autowired
    private OrderRepository    orderRepository;
    @Autowired
    private BookService        bookService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SaleRepository     saleRepository;


    public void startPurchase(String email, long idBook, String nameLibrary)
    {
        Customer customer  = (Customer) userRepository.findByEmail(email);
        CopyBook copyBook  = copyBookRepository.findById(idBook).get();
        Book book          = bookService.findByIsbn(copyBook.getIsbn());


        copyBook.setStatus(CopyBook.Status.ENPROCESSSALE);
        copyBookRepository.save(copyBook);

        Order order = new Order(book,
                                copyBook,
                                email,
                                nameLibrary);

        orderRepository.save(order);
        customer.getShoppingCart().add(order);
        userRepository.save(customer);
    }

    public void processPurchase(String email){
        Customer customer  = (Customer) userRepository.findByEmail(email);

        List<Order> orders =  customer.getShoppingCart();

        for(Order order: orders)
        {
            CopyBook copyBook =
                    order.getCopyBook();
            Book book =
                    order.getBook();
            Library library =
                    libraryRepository.findByName(order.getNameLibrary());

            Purchase purchase = new Purchase(book, copyBook.getId(), order.getNameLibrary(), copyBook.getCondition(),
                    copyBook.getDescription(), copyBook.getSite(), copyBook.getPrice());

            customer.getPurchases().add(purchase);
            purchaseRepository.save(purchase);

            Sale sale = new Sale(book, copyBook, email, purchase.getId());
            library.getSales().add(sale);
            saleRepository.save(sale);

        }

        for(int i = 0; !orders.isEmpty(); ++i)
            eliminateOrder(orders.get(0).getId());


    }



    public void eliminateOrder(long idOrder)
    {
        Order order       = orderRepository.findById(idOrder).get();
        Customer customer = (Customer) userRepository.findByEmail(order.getEmail());

        customer.getShoppingCart().remove(order);
        userRepository.save(customer);
        order.getCopyBook().setStatus(CopyBook.Status.AVAILABLE);
        copyBookRepository.save(order.getCopyBook());
        orderRepository.delete(order);
    }































    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void save(User user)
    {
        userRepository.save(user);
    }

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
}
