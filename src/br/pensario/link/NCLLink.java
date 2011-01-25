package br.pensario.link;

import br.pensario.NCLDoc;
import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLParamInstance;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.connector.NCLCausalConnector;
import java.util.Iterator;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>link</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um elo de um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.1
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLLink<L extends NCLLink, P extends NCLParam, B extends NCLBind, C extends NCLCausalConnector> extends NCLIdentifiableElement implements Comparable<L>{

    private C xconnector;
    
    private Set<P> linkParams = new TreeSet<P>();
    private Set<B> binds = new TreeSet<B>();
    

    /**
     * Construtor do elemento <i>link</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLLink() {}


    /**
     * Construtor do elemento <i>link</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLLink(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um conector ao link.
     * 
     * @param xconnector
     *          conector a ser atribuido ao link.
     */
    public void setXconnector(C xconnector) {
        this.xconnector = xconnector;
    }
    
    
    /**
     * Retorna o conector do link.
     * 
     * @return
     *          conector atribuido ao link.
     */
    public C getXconnector() {
        return xconnector;
    }
    
    
    /**
     * Adiciona um parâmetro ao link.
     * 
     * @param param
     *          elemento representando o parâmetro a ser adicionado.
     * @return
     *          verdadeiro se o parâmetro foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addLinkParam(P param) {
        return linkParams.add(param);
    }
    
    
    /**
     * Remove um parâmetro do link.
     * 
     * @param param
     *          elemento representando o parâmetro a ser removido.
     * @return
     *          verdadeiro se o parâmetro foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeLinkParam(P param) {
        return linkParams.remove(param);
    }
    
    
    /**
     * Verifica se o link possui um parâmetro.
     * 
     * @param param
     *          elemento representando o parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasLinkParam(P param) {
        return linkParams.contains(param);
    }
    
    
    /**
     * Verifica se o link possui algum parâmetro.
     * 
     * @return
     *          verdadeiro se o link possui algum parâmetro.
     */
    public boolean hasLinkParam() {
        return !linkParams.isEmpty();
    }
    
    
    /**
     * Retorna os parâmetros do link.
     * 
     * @return
     *          objeto Iterable contendo os parâmetros do link.
     */
    public Iterable<P> getLinkParams() {
        return linkParams;
    }
    
    
    /**
     * Adiciona um bind ao link.
     * 
     * @param bind
     *          elemento representando o bind a ser adicionado.
     * @return
     *          verdadeiro se o bind for adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addBind(B bind) {
        return binds.add(bind);
    }
    
    
    /**
     * Remove um bind do link.
     * 
     * @param bind
     *          elemento representando o bind a ser removido.
     * @return
     *          verdadeiro se o bind for removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeBind(B bind) {
        return binds.remove(bind);
    }
    
    
    /**
     * Verifica se o link possui um bind.
     * 
     * @param bind
     *          elemento representando o bind a ser verificado.
     * @return
     *          verdadeiro se o bind existir.
     */
    public boolean hasBind(B bind) {
        return binds.contains(bind);
    }


    /**
     * Verifica se o link possui algum bind.
     *
     * @return
     *          verdadeiro se o link possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }
    
    
    /**
     * Retorna os binds do link
     * 
     * @return
     *          objeto Iterable contendo os binds do link.
     */
    public Iterable<B> getBinds() {
        return binds;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <link> element and attributes declaration
        content = space + "<link";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getXconnector() != null)
            content += " xconnector='" + getXconnector().getId() + "'";
        content += ">\n";
        
        // <link> element content
        if(hasLinkParam()){
            for(P param : linkParams)
                content += param.parse(ident + 1);
        }
        if(hasBind()){
            for(B bind : binds)
                content += bind.parse(ident + 1);
        }

        // <link> element end declaration
        content += space + "</link>\n";
        
        return content;
    }


    public int compareTo(L other) {
        int comp = 0;

        String this_link, other_link;

        // Compara pelo xconnector
        comp = getXconnector().compareTo(other.getXconnector());

        // Compara o número de parâmetros
        if(comp == 0)
            comp = linkParams.size() - ((Set) other.getLinkParams()).size();

        // Compara o número de binds
        if(comp == 0)
            comp = binds.size() - ((Set) other.getBinds()).size();

        // Compara os parâmetros
        if(comp == 0){
            Iterator it = other.getLinkParams().iterator();
            for(P param : linkParams){
                P other_param = (P) it.next();
                comp = param.compareTo(other_param);
                if(comp != 0)
                    break;
            }
        }

        // Compara os binds
        if(comp == 0){
            Iterator it = other.getBinds().iterator();
            for(B bind : binds){
                B other_bind = (B) it.next();
                comp = bind.compareTo(other_bind);
                if(comp != 0)
                    break;
            }
        }


        return comp;
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getXconnector() != null);
        valid &= (binds.size() >= 2);

        if(hasLinkParam()){
            for(P param : linkParams){
                valid &= param.validate();
                valid &= (param.getType().equals(NCLParamInstance.LINKPARAM));
            }
        }
        if(hasBind()){
            for(B bind : binds)
                valid &= bind.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("link")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("xconnector"))
                        setXconnector((C) new NCLCausalConnector(attributes.getValue(i)));
                }
            }
            else if(localName.equals("linkParam")){
                NCLParam p = new NCLParam(NCLParamInstance.LINKPARAM, getReader(), this);
                p.startElement(uri, localName, qName, attributes);
                addLinkParam((P) p); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("bind")){
                NCLBind b = new NCLBind(getReader(), this);
                b.startElement(uri, localName, qName, attributes);
                addBind((B) b);//TODO: retirar o cast. Como melhorar isso?
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getXconnector() != null)
                connectorReference();
        }

        if(hasLinkParam()){
            for(P param : linkParams){
                param.endDocument();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }
        if(hasBind()){
            for(B bind : binds){
                bind.endDocument();
                addWarning(bind.getWarnings());
                addError(bind.getErrors());
            }
        }
    }


    private Iterable<C> getConnectors() {
        NCLElement root = getParent();

        while(!(root instanceof NCLDoc)){
            root = root.getParent();
            if(root == null){
                addWarning("Could not find a root element");
                return null;
            }
        }

        if(((NCLDoc) root).getHead() == null){
            addWarning("Could not find a head");
            return null;
        }
        if(((NCLDoc) root).getHead().getConnectorBase() == null){
            addWarning("Could not find a connectorBase");
            return null;
        }

        return ((NCLDoc) root).getHead().getConnectorBase().getCausalConnectors();
    }


    private void connectorReference() {
        //Search for the connector inside the base
        Iterable<C> connectors = getConnectors();
        if(connectors == null){
            addWarning("Could not find connector in connectorBase with id: " + getXconnector().getId());
            return;
        }

        for(C connector : connectors){
            if(connector.getId().equals(getXconnector().getId())){
                setXconnector(connector);
                return;
            }
        }

        addWarning("Could not find connector in connectorBase with id: " + getXconnector().getId());
    }
}
