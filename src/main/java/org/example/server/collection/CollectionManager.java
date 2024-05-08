package org.example.server.collection;

import lombok.Getter;
import lombok.Setter;
import org.example.contract.model.Product;
import org.example.contract.model.ProductDTO;
import org.example.server.utils.ProductComparator;

import java.util.*;
@Getter
@Setter
public class CollectionManager {
    private Set<Product> products;
    private long lastId = 0;

    public CollectionManager(Set<Product> productCollection) {
        this.products = productCollection;
    }

    public void add(ProductDTO productDTO) {
        products.add(new Product(generateId(), productDTO));
    }

    public void removeById(long id) {
        Product productDelete = null;
        for (Product p : products) {
            if (p.getId() == id) {
                productDelete = p;
            }
        }
        products.remove(productDelete);
    }

    public void updateById(long id, ProductDTO productDTO) {
        removeById(id);
        Product product = new Product(id, productDTO);
        products.add(product);
        ProductComparator productComparator = new ProductComparator();
        sort(productComparator);
    }

    public long generateId() {
        return ++lastId;
    }
    public long getMaxId(){
        long maxId = -1;
        if(products.isEmpty()){
            return lastId;
        }
        else{
            for(Product p: products){
                if(p.getId() > maxId) maxId = p.getId();
            }
            return maxId;
        }

    }

    public int getSize() {
        return products.size();
    }

    public void sort(Comparator<Product> comparator) {
        List<Product> list = new ArrayList<>();
        for(Product p: products){
            list.add(p);
        }
        list.sort(comparator);
        products = new LinkedHashSet<>(list);

    }

    public boolean isIdExists(long idToFind) {
        boolean flag = false;
        for (Product p : products) {
            if (p.getId() == idToFind) flag = true;
        }
        return flag;

    }

    public boolean checkIfMin(ProductDTO productDTO) {
        ProductComparator productComparator = new ProductComparator();
        sort(productComparator);
        Product p = new Product(generateId(), productDTO);
        List<Product> list = new ArrayList<>(products);
        if (productComparator.compare(p, list.get(0)) < 0) {
            return true;
        } else {
            return false;
        }

    }

    public void removeGreater(ProductDTO productDTO) {
        Product p = new Product(generateId(), productDTO);
        ProductComparator productComparator = new ProductComparator();
        for (Product product : products) {
            if (productComparator.compare(product, p) > 0) {
                products.remove(product);
            }

        }

    }

    public void removeLower(ProductDTO productDTO) {
        Product p = new Product(generateId(), productDTO);
        ProductComparator productComparator = new ProductComparator();
        for (Product product : products) {
            if (productComparator.compare(product, p) < 0) {
                products.remove(product);
            }

        }

    }

    public void clear() {
        products.clear();
    }

    public void save() {
        Storage.save(products,
                "collection");

    }
}
