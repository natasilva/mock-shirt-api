package com.mockshirt.mockshirt.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.mockshirt.mockshirt.entity.Shirt;
import com.mockshirt.mockshirt.form.FormData;

public interface IShirtService {
    List<Shirt> list(FormData formData) throws IOException;
}
