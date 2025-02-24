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
package org.neo4j.graphalgo.core.loading.construction;

import org.neo4j.graphalgo.utils.CheckedRunnable;
import org.neo4j.graphalgo.utils.GdsFeatureToggles;

import java.util.stream.Stream;

import static org.neo4j.graphalgo.GdsEditionUtils.setToEnterpriseAndRun;

public interface TestMethodRunner {
    <E extends Exception> void run(CheckedRunnable<E> code) throws E;

    static Stream<TestMethodRunner> idMapImplementation() {
        TestMethodRunner defaultImpl = CheckedRunnable::checkedRun;
        TestMethodRunner bitImMapImpl = TestMethodRunner::runWithBitIdMap;
        return Stream.of(defaultImpl, bitImMapImpl);
    }

    static <E extends Exception> void runWithBitIdMap(CheckedRunnable<E> code) throws E {
        setToEnterpriseAndRun(() -> GdsFeatureToggles.USE_BIT_ID_MAP.enableAndRun(code));
    }

    static Stream<TestMethodRunner> adjacencyCompressions() {
        TestMethodRunner compressed = CheckedRunnable::checkedRun;
        TestMethodRunner uncompressed = TestMethodRunner::runWithUncompressedAdjacencyList;
        return Stream.of(compressed, uncompressed);
    }

    static <E extends Exception> void runWithUncompressedAdjacencyList(CheckedRunnable<E> code) throws E {
        setToEnterpriseAndRun(() -> GdsFeatureToggles.USE_UNCOMPRESSED_ADJACENCY_LIST.enableAndRun(code));
    }
}
