/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
package org.hibernate.hql.internal.ast.exec;

import org.hibernate.HibernateException;
import org.hibernate.action.internal.BulkOperationCleanupAction;
import org.hibernate.engine.spi.AsyncSessionImplementor;
import org.hibernate.engine.spi.QueryParameters;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.event.spi.EventSource;
import org.hibernate.hql.internal.ast.HqlSqlWalker;
import org.hibernate.hql.spi.id.MultiTableBulkIdStrategy;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of MultiTableUpdateExecutor.
 *
 * @author Steve Ebersole
 */
public class MultiTableUpdateExecutor implements StatementExecutor {
	private final MultiTableBulkIdStrategy.UpdateHandler updateHandler;

	public MultiTableUpdateExecutor(HqlSqlWalker walker) {
		MultiTableBulkIdStrategy strategy = walker.getSessionFactoryHelper()
				.getFactory()
				.getSettings()
				.getMultiTableBulkIdStrategy();
		this.updateHandler = strategy.buildUpdateHandler( walker.getSessionFactoryHelper().getFactory(), walker );
	}

	public String[] getSqlStatements() {
		return updateHandler.getSqlStatements();
	}

	public int execute(QueryParameters parameters, SessionImplementor session) throws HibernateException {
		BulkOperationCleanupAction action = new BulkOperationCleanupAction( session, updateHandler.getTargetedQueryable() );

		if ( session.isEventSource() ) {
			( (EventSource) session ).getActionQueue().addAction( action );
		}
		else {
			action.getAfterTransactionCompletionProcess().doAfterTransactionCompletion( true, session );
		}

		return updateHandler.execute( session, parameters );
	}

    @Override
    public CompletableFuture<Integer> executeAsync(QueryParameters queryParameters, AsyncSessionImplementor asyncSessionImplementor) throws HibernateException {
        throw new UnsupportedOperationException("Multi table updates are not supported in async mode");
        // ... because multi table actions require temp tables created with DDL commands
    }
}
