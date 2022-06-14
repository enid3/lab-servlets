package by.bsu.soroka.ea.neko.dao.sqlite.impl;

import by.bsu.soroka.ea.neko.dao.interfaces.AdvancedProductDAO;
import by.bsu.soroka.ea.neko.dao.interfaces.BasicDAO;
import by.bsu.soroka.ea.neko.dao.exception.DAOException;
import by.bsu.soroka.ea.neko.dao.sqlite.connection.ConnectionProvider;
import by.bsu.soroka.ea.neko.entity.Product;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SQLProductDAO implements BasicDAO<Product>, AdvancedProductDAO {

    private final static String TABLE_NAME = "product"; // {0}

    // Column names;
    // |ID_COLUMN|NAME_COLUMN|PRICE_COLUMN|
    private final static String ID_COLUMN = "id";           // {1}
    private final static String NAME_COLUMN = "name";       // {2}
    private final static String RATING_COLUMN = "rating";   // {3}
    private final static String IMAGE_COLUMN = "image";     // {4}

    // SQL requests

    private final String ADD = MessageFormat.format(
            "INSERT INTO {0} ({2}, {3}, {4}) VALUES(?,?,?)",
            TABLE_NAME, ID_COLUMN, NAME_COLUMN, RATING_COLUMN, IMAGE_COLUMN);
    private final String UPDATE = MessageFormat.format(
            "UPDATE {0} " +
                    "SET {2}=?, {3}=?, {4}=? " +
                    "WHERE {1}=?",
            TABLE_NAME, ID_COLUMN, NAME_COLUMN, RATING_COLUMN, IMAGE_COLUMN);

    public static List<Product> parseResultSetToProducts(ResultSet rs) throws SQLException {
        log.debug("Result set: {}", rs);
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(new Product(
                    rs.getInt(ID_COLUMN),
                    rs.getString(NAME_COLUMN),
                    rs.getInt(RATING_COLUMN),
                    rs.getBytes(IMAGE_COLUMN)
            ));
        }
        log.debug("Result list: {}", products);
        return products;
    }

    @Override
    public List<Product> getAll() throws DAOException {
        Connection connection = ConnectionProvider.getInstance().getConnection();
        List<Product> list = new ArrayList<>();
        try {
            log.debug("Getting all Products");
            list = parseResultSetToProducts(SQLCommonDAO.getAllOrdered(TABLE_NAME,
                    RATING_COLUMN + " DESC"));

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public int add(Product product) throws DAOException {
        Connection connection = ConnectionProvider.getInstance().getConnection();
        try {
            log.debug("Adding product {}", product);
            log.debug("ADD: {}", ADD);

            PreparedStatement ps = connection.prepareStatement(ADD);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getRating());
            ps.setBytes(3, product.getImage());

            log.debug("Executing statement: {}", ps.getParameterMetaData());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            log.debug("Result set: {}", rs);

            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            product.setId(id);
            log.debug("ID of {} was chagned to {}", product, id);
            return id;

        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public boolean removeById(int id) throws DAOException {
        return SQLCommonDAO.deleteByColumnName(TABLE_NAME, ID_COLUMN, id) > 0;
    }

    @Override
    public boolean cascadeRemoveByID(int id) throws DAOException {
        return removeById(id);
    }

    @Override
    public int update(Product product) throws DAOException {
        Connection connection = ConnectionProvider.getInstance().getConnection();
        try {
            log.debug("Updating product {}", product);

            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setInt(4, product.getId());

            ps.setString(1, product.getName());
            ps.setInt(2, product.getRating());
            ps.setBytes(3, product.getImage());


            log.debug("Executing statement: {}", ps.getParameterMetaData());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            log.debug("Result set: {}", rs);

            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            product.setId(id);
            log.debug("ID of {} is {}", product, id);
            return id;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
