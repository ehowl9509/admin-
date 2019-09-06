package com.example.admin1.interfaces;

import com.example.admin1.model.network.Header;

public interface CrudInterface {

    Header create();

    Header read(Long id);

    Header update();

    Header delete(Long id);


}
