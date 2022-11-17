package model.dao;

import model.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
    static Connection connection = ConnectMySql.getConnection();

    public static List<Product> getAll() {
        String selectAll = "SELECT * FROM demo_qlsp_module4.products";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAll);
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                Double price = resultSet.getDouble("price");
                products.add(new Product(id,name,img,price));
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveProduct(Product product){
        String insert = "INSERT INTO `demo_qlsp_module4`.`products` (`name`, `img`, `price`) VALUES (?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getImg());
            preparedStatement.setDouble(3,product.getPrice());

            preparedStatement.execute();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {
        try {
            String sql = "DELETE FROM  `demo_qlsp_module4`.`products`  WHERE (id= ?)";
            Connection connection = ConnectMySql.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editProduct(int id,Product product) {
        try{
            String sql = "UPDATE `demo_qlsp_module4`.`products` SET `name` = ?, `img` = ?, `price` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getImg());
            preparedStatement.setDouble(3,product.getPrice());
            preparedStatement.setInt(4,product.getId());
            preparedStatement.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
