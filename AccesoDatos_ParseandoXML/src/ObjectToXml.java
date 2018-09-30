import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ObjectToXml{
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		
		
		
		Empleado e1 = new Empleado("Roger", 2500);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = factory.newDocumentBuilder();
		
		Document doc = build.newDocument();
		Element rootElement = doc.createElement("Empleados");
		doc.appendChild(rootElement);
		
		
		Element empleado = doc.createElement(e1.getClass().getSimpleName());
		rootElement.appendChild(empleado);
		
		Element nombre = doc.createElement("Nombre");
		nombre.appendChild(doc.createTextNode(e1.getNombre()));
		empleado.appendChild(nombre);
		
		Element sueldo = doc.createElement("Sueldo");
		sueldo.appendChild(doc.createTextNode(e1.getSueldo()+""));
		empleado.appendChild(sueldo);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		DOMSource dom = new DOMSource(doc);
		File f = new File("/home/roger/Escritorio/empleado.xml");
		StreamResult sr = new StreamResult(f);
		trans.transform(dom, sr);
		
	}
}
