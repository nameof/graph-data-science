/*
 * Copyright (c) "Neo4j"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.graphalgo.nodeproperties;

import org.eclipse.collections.api.block.function.primitive.LongToLongFunction;
import org.neo4j.graphalgo.api.nodeproperties.LongNodeProperties;

public final class LongTestProperties implements LongNodeProperties {
    private final LongToLongFunction transformer;

    public LongTestProperties(LongToLongFunction transformer) {this.transformer = transformer;}

    @Override
    public long size() {
        return 0;
    }

    @Override
    public long longValue(long nodeId) {
        return transformer.applyAsLong(nodeId);
    }
}
