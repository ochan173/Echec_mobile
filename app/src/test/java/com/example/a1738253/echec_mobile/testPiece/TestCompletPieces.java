package com.example.a1738253.echec_mobile.TestPiece;


import junit.framework.TestSuite;

/**
 * Classe qui exécute tous les tests pour les pièces.
 *
 * @author Olivier Chan
 * @author David Goulet
 */
public class TestCompletPieces extends TestSuite {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestCavalier.class);
        suite.addTestSuite(TestFou.class);
        suite.addTestSuite(TestPionBlanc.class);
        suite.addTestSuite(TestPionNoir.class);
        suite.addTestSuite(TestReine.class);
        suite.addTestSuite(TestRoi.class);
        suite.addTestSuite(TestTour.class);
        return suite;
    }
}
