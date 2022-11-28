package com.example.softwaretesting.data.serializer;

import com.example.softwaretesting.data.entity.ServiceUser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ServiceUserMapper extends JsonSerializer<ServiceUser> {

	@Override
	public void serialize(ServiceUser t, JsonGenerator jg, SerializerProvider sp) throws IOException {
		jg.writeStartObject();
		jg.writeStringField("name" +
				"", t.getName());
		jg.writeEndObject();
	}

}