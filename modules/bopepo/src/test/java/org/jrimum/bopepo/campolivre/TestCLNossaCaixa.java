/* 
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created at: 03/10/2008 - 11:39:10
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode 
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma 
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que 
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta 
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam 
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões 
 * e limitações sob esta LICENÇA.
 * 
 * Criado em: 03/10/2008 - 11:39:10
 * 
 */

package org.jrimum.bopepo.campolivre;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Modalidade;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Teste unitário do campo livre do banco nossa caixa
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class TestCLNossaCaixa extends AbstractCampoLivreBaseTest<CLNossaCaixa> {

	@Before
	public void setUp(){
		
		
		titulo.getContaBancaria().setBanco(BancosSuportados.NOSSA_CAIXA.create());
		titulo.getContaBancaria().setAgencia(new Agencia(1, "1"));
		titulo.getContaBancaria().setNumeroDaConta(new NumeroDaConta(2818));
		titulo.getContaBancaria().setModalidade(new Modalidade(13));
		titulo.setNossoNumero("997654321");
		titulo.setDigitoDoNossoNumero("1");
		
		createCampoLivreToTest();
		
		setCampoLivreEsperadoComoString("9976543210001300281815107");
	}
	
	@Test(expected = CampoLivreException.class)
	public void testCodigoDaModalidadeNull() {
	
		Integer codigo = null;
		titulo.getContaBancaria().getModalidade().setCodigo(codigo);
		
		createCampoLivreToTest();
	}
	
	@Test(expected = CampoLivreException.class)
	public void testCodigoDaModalidadeInvalido() {
	
		titulo.getContaBancaria().getModalidade().setCodigo(10);
		
		createCampoLivreToTest();
	}
}
