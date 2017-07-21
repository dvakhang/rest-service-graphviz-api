package com.dounets.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dounets.graphviz.GraphViz;
import com.dounets.model.Graph;

@RestController
public class GreetingController {
    
    @RequestMapping("/getGraph")
	public Map<String, Object> get(@RequestBody Graph reqVo) {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		for (String node : reqVo.getNode()) {
			gv.addln(node);
		}
		gv.addln(gv.end_graph());
		gv.increaseDpi(); 
		String type = "png";
		
		String repesentationType= "dot";
		Date dt = new Date();
		
		File out = new File("D:/Project/ML/Graphiviz/rest-service-graphviz-api/tmp/out"+dt.getTime()+"."+ type);   // Windows
		gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resultImage", gv.getGraph(gv.getDotSource(), type, repesentationType));
		return model;
	}
}
