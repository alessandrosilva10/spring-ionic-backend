package br.com.alessandro.alga.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alessandro.alga.event.URIEvent;


@Component
public class URIEventListener implements ApplicationListener<URIEvent> {

	@Override
	public void onApplicationEvent(URIEvent uriEvent) {
		HttpServletResponse response = uriEvent.getResponse();
		Long codigo = uriEvent.getCodigo();
		
		adicionarHeaderLocation(response, codigo);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
			
		response.setHeader("Location", uri.toASCIIString());
	}
}
