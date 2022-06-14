package by.bsu.soroka.ea.neko.entity;

import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.StorageService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StorageContainer implements Serializable {
    private final static StorageService ps = ServiceProvider.getInstance().getStorageService();

    private List<Storage> data = new ArrayList<>();

    public StorageContainer() throws ServiceException {
        data = ps.getAll();
    }

    public List<Storage> getData() {
        return new ArrayList<>(data);
    }
}
