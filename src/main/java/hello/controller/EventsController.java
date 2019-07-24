package hello.controller;

import hello.EventNotFoundException;
import hello.model.Event;
import hello.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class EventsController {

	@Autowired
	EventsRepository eventsRepository;

	// GOALS //
	// GET /events tutti gli eventi FATTO
	// Get /events/1 evento con id 1 FATTO
	// POST /events crea nuovo evento FATTO
	// PUT /events/1 update evento 1 FATTO
	// DELETE /events/1 cancella evento 1 FATTO

	@GetMapping("/events")
	public List<Event> fetchEvents() {
		return eventsRepository.findAll();
	}

	@PostMapping("/events/add")
	public Event addEvents(@RequestBody Event events) {
		eventsRepository.save(events);
		return events;
	}

	@GetMapping("/events/{id}")
	public Optional<Event> searchEvents(@PathVariable long id) {
		Optional<Event> srcEventOptional = eventsRepository.findById(id);
		if (!srcEventOptional.isPresent()) {
			throw new EventNotFoundException();
		}

		return srcEventOptional;

	}

	@PutMapping("/events/{id}")
	public ResponseEntity<Object> updateEvents(@PathVariable long id, @RequestBody Event events) {
		Optional<Event> uptEventOptional = eventsRepository.findById(id);

		if (!uptEventOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		events.setId(id);
		eventsRepository.save(events);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/events/{id}/delete")
	public ResponseEntity<Object> deleteEvents(@PathVariable long id) {
		Optional<Event> delEventOptional = eventsRepository.findById(id);

		if (!delEventOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		eventsRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
