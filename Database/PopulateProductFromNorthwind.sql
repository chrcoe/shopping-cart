INSERT INTO cart_comp461_db.Product (name, CategoryName, price, amt_in_stock, amt_on_order, reorder_threshold, is_discontinued)
SELECT p.ProductName, c.CategoryName, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.ReorderLevel, p.Discontinued
FROM Northwind.Products p, Northwind.Categories c 
where c.CategoryID=p.CategoryID;