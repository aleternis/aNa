/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.datatype.ncl.interfaces;

import br.uff.midiacom.ana.datatype.auxiliar.ReferenceType;
import br.uff.midiacom.ana.datatype.auxiliar.SampleType;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.array.ArrayType;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.util.TreeSet;


public class NCLAreaPrototype<T extends NCLAreaPrototype, P extends NCLElement, I extends NCLElementImpl, Ei extends NCLInterface>
        extends NCLIdentifiableElementPrototype<Ei, P, I> implements NCLInterface<Ei, P> {

    protected ArrayType coords;
    protected TimeType begin;
    protected TimeType end;
    protected StringType text;
    protected Integer position;
    protected SampleType first;
    protected SampleType last;
    protected StringType label;
    
    protected TreeSet<ReferenceType> references;
    
    
    /**
     * Construtor do elemento <i>area</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador da âncora.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da âncora for inválido.
     */
    public NCLAreaPrototype(String id) throws XMLException {
        super();
        setId(id);
        references = new TreeSet<ReferenceType>();
    }


    public NCLAreaPrototype() throws XMLException {
        super();
        references = new TreeSet<ReferenceType>();
    }


    /**
     * Define uma âncora de conteudo representando porções espaciais.
     * x1,y1,x2,y2,...,xN,yN
     * 
     * @param coords
     *          coordenadas da âncora espacial.
     */
    public void setCoords(ArrayType coords){
        this.coords = coords;
    }
    
    
    /**
     * Retorna as coordenadas espaciais da âncora.
     * 
     * @return
     *          array de inteiros com as coordenadas.
     */
    public ArrayType getCoords() {
        return coords;
    }
    
    
    /**
     * Define uma âncora de conteúdo representando porções temporais.
     * Caso a media que contém a âncora for do tipo "application/x-ginga-time" o tempo deve ser completo.
     * 
     * @param begin
     *          tempo de inicio da âncora temporal.
     *
     * @see TimeType
     */
    public void setBegin(TimeType begin) {
        this.begin = begin;
    }
    
    
    /**
     * Retorna o valor de inicio da âncora.
     * 
     * @return
     *          elemento representando o valor de tempo.
     */
    public TimeType getBegin() {
        return begin;
    }
    
    
    /**
     * Define uma âncora de conteúdo representando porções temporais.
     * Caso a media que contém a âncora for do tipo "application/x-ginga-time" o tempo deve ser completo.
     * 
     * @param end
     *          tempo de fim da âncora temporal.
     *
     * @see TimeType
     */
    public void setEnd(TimeType end) {
        this.end = end;
    }
    
    
    /**
     * Retorna o valor de fim da âncora.
     * 
     * @return
     *          elemento representando o valor de tempo.
     */
    public TimeType getEnd() {
        return end;
    }
    
    
    /**
     * Define uma ancora textual.
     * 
     * @param text
     *          padrao de texto usado como âncora textual.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setText(String text) throws XMLException {
        this.text = new StringType(text);
    }
    
    
    /**
     * Retorna o conteúdo da ancora textual.
     * 
     * @return
     *          String com conteúdo da âncora textual.
     */
    public String getText() {
        if(text != null)
            return text.getValue();
        else
            return null;
    }
    
    
    /**
     * Define a posição do texto da ancora textual.
     * 
     * @param position
     *          posicao do texto.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for um valor negativo.
     */
    public void setPosition(Integer position) throws XMLException {
        if(position != null && position < 0)
            throw new XMLException("Invalid position");
        this.position = position;
    }
    
    
    /**
     * Retorna a posição do texto da ancora textual.
     * 
     * @return
     *          inteiro com a posicao do texto.
     */
    public Integer getPosition() {
        return position;
    }
    
    
    /**
     * Define uma ancora de conteudo com base em uma amostra.
     * 
     * @param first
     *          amostra de inicio da ancora.
     *
     * @see SampleType
     */
    public void setFirst(SampleType first) {
        this.first = first;
    }
    
    
    /**
     * Retorna o início da ancora de conteudo com base em uma amostra.
     * 
     * @return
     *          amostra de inicio da ancora.
     */
    public SampleType getFirst() {
        return first;
    }
    
    
    /**
     * Define uma ancora de conteudo com base em uma amostra.
     *
     * @param last
     *          amostra de fim da ancora.
     *
     * @see SampleType
     */
    public void setLast(SampleType last) {
        this.last = last;
    }
    
    
    /**
     * Retorna o fim da ancora de conteudo com base em uma amostra.
     *
     * @return
     *          amostra de fim da ancora.
     */
    public SampleType getLast() {
        return last;
    }
    
    
    /**
     * define uma ancora de conteudo baseada no atributo label,
     * que especifica uma cadeia de caracteres que deve ser utilizada
     * pelo exibidor de midias para identificar uma regiao de conteudo.
     * 
     * @param label
     *          identificacao da região do conteúdo.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setLabel(String label) throws XMLException {
        this.label = new StringType(label);
    }
    
    
    /**
     * Retorna o label da ancora de conteudo.
     * 
     * @return
     *          String contendo a identificacao da região do conteúdo.
     */
    public String getLabel() {
        if(label != null)
            return label.getValue();
        else
            return null;
    }
    
    
    public boolean addReference(ReferenceType reference) {
        return references.add(reference);
    }
    
    
    public boolean removeReference(ReferenceType reference) {
        return references.remove(reference);
    }
    
    
    public TreeSet<ReferenceType> getReferences() {
        return references;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
                
        // <area> element and attributes declaration
        content = space + "<area";
        content += parseAttributes();
        content += "/>\n";
        
        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseCoords();
        content += parseBegin();
        content += parseEnd();
        content += parseText();
        content += parsePosition();
        content += parseFirst();
        content += parseLast();
        content += parseLabel();
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseCoords() {
        ArrayType aux = getCoords();
        if(aux != null)
            return " coords='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseBegin() {
        TimeType aux = getBegin();
        if(aux != null)
            return " begin='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseEnd() {
        TimeType aux = getEnd();
        if(aux != null)
            return " end='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseText() {
        String aux = getText();
        if(aux != null)
            return " text='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parsePosition() {
        Integer aux = getPosition();
        if(aux != null)
            return " position='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseFirst() {
        SampleType aux = getFirst();
        if(aux != null)
            return " first='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseLast() {
        SampleType aux = getLast();
        if(aux != null)
            return " last='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseLabel() {
        String aux = getLabel();
        if(aux != null)
            return " label='" + aux + "'";
        else
            return "";
    }
}
